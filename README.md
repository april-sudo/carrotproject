# carrotproject

## 이벤트 스토밍 결과 
https://labs.msaez.io/#/storming/eNH2vbsQ2VdRIxex0eP2mQsnr9v2/fa2f9d4b102733c541232239642105f1


## 기능적 요구사항

1. 판매자가 물건을 등록/ 수정 / 삭제 한다.
2. 구매자가 물건을 조회할 수 있다.
3. 구매자가 판매자에게 채팅을 요청하여 예약을 생성한다.
4. 판매자 물건 상태 예약한다. ( 약속 날짜, 시간 , 장소 )
5. 구매자가 결재를 완료하면 완료상태로 변경되고 상품은 판매상태로 변경된다.
6. 결재가 취소되면 완료에서 예약컨펌 상태로 변경되고 상품은 판매중으로 변경된다.
7. 거래가 완료되면 구매자는 상품에 대한 리뷰를 작성할 수 있고, 작성된리뷰가 상품의 스코어로 반영된다.
8. MyPage에서 등록된 물건과 거래상태를 조회할 수 있다. . 비기능 . 트랜젝션
9. 거래완료된 물건은 채팅 요청이 불가능해야 한다.
10. 예약 컨펌상태인 item은 삭제할 수 없다.

## 장애격리
1. 결제시스템이 수행되지 않더라도 item service는 운영되어야 한다. 
3. reservation 시스템이 과중되면 잠시후에 하도록 유도한다.

## 성능
- 모든 제품에 대한 정보 및 예약 상태 등을 한번에 확인할 수 있어야 한다 (CQRS)

## item의 상태
- created : 판매자가 물건을 등록한 상태
- reserved : 예약이 완료된 상태
- soldout : 결재 완료된 상태

## reservation의 상태
- created : 대화창이 생성된 상태
- confirmed : 예약이 완료된 상태 
- completed : 결재 완료된 상태


## kafka 올리기 
- helm 차트를 이용해서 올림
- 다시 올려야 할 경우 : helm upgrade my-kafka bitnami/kafka

kafka-console-consumer.sh \
--bootstrap-server my-kafka.kafka.svc.cluster.local:9092 \
--topic carrotproject \
--from-beginning

## CQRS 
1. item registered
```
http :8083/items name="tv"
http :8088/myPages 
```
2. item deleted
```
http DELETE :8088/items/2
http :8088/myPages 
```
3. reservation created
```
http :8082/reservations itemId=1 rsvStatus="CREATED" rsvPlace="seoul"
```
4. reservation confirmed
```
http PATCH :8082/reservations/1 rsvStatus="CONFIRMED" 
```
5. reservation deleted 
```
http DELETE :8082/reservations/1
```
6. payment approved, item sold out
```
http :8081/payments rsvId=1 itemId=1 status="APPROVED"
```
7. payment cancelled 
```
http PATCH :8081/payments/1 rsvId=1 itemId=1 status="DELETED"
```

## timeout test
1. timeout istio policy 생성 (/kubernetes/istio-pocliy.yaml) 0.3s 
2. seige를 통한 부하 생성 
```
kubectl exec -it siege-88f7fdd8d-vncwx  -c siege-nginx -- /bin/bash
siege -c90 -t7S -v --content-type "application/json" 'http://reservation:8080/reservations'
```

## retry test case
1. reservation 생성시에 존재하지 않는 item id 사용
```
kubectl exec -it siege-88f7fdd8d-vncwx -c siege-nginx -- /bin/bash
http POST http://reservation:8080/reservations rsvPlace="seoul" itemId=1000 rsvStatus="CREATED" #must fail , itemId is not exist!!
```
2. http://jaeger.service.com 에서 retry 확인 (siege.default service 선택)


## hpa
1. auto scale 정책 생성
```
kubectl autoscale deployment item --cpu-percent=20 --min=1 --max=3
```
2. deployment 설정에 request option 추가
3. auto scale out 증명
```
siege -c30 -t20S -v --content-type "application/json" 'http://item:8080/items'
```

## Zero-downtime deploy (readiness probe)
1. deployment.yml에서 item의 이미지 v2->canary로 변경
2. siege로 부하 생성 
```
siege -c1 -t60S -v http://item:8080/items --delay=1S
```
3. kubectl apply -f deployment.yml 
4. kubectl get po -w로 확인
5. siege 결과 100% 확인

## Self-healing (liveness probe)
1. deployment.yml에서 item의 이미지 v2 확인
2. kubectl apply -f deployment.yml 
3. kubectl get po -w
4. http item:8080/items/callMemleak
5. restart 확인

