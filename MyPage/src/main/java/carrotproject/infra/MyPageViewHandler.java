package carrotproject.infra;

import carrotproject.domain.*;
import carrotproject.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MyPageViewHandler {


    @Autowired
    private MyPageRepository myPageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenItemRegistered_then_CREATE_1 (@Payload ItemRegistered itemRegistered) {
        try {

            if (!itemRegistered.validate()) return;

            // view 객체 생성
            MyPage myPage = new MyPage();
            // view 객체에 이벤트의 Value 를 set 함
            myPage.setItemName(itemRegistered.getName());
            myPage.setItemStatus(itemRegistered.getStatus());
            myPage.setItemId(itemRegistered.getItemId());
            // view 레파지 토리에 save
            myPageRepository.save(myPage);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenItemModified_then_UPDATE_1(@Payload ItemModified itemModified) {
        try {
            if (!itemModified.validate()) return;
                // view 객체 조회
            Optional<MyPage> myPageOptional = myPageRepository.findById(itemModified.getItemId());

            if( myPageOptional.isPresent()) {
                 MyPage myPage = myPageOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                 myPage.setItemName(itemModified.getName());
                // view 레파지 토리에 save
                 myPageRepository.save(myPage);
                }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenItemSoldOutConfirmed_then_UPDATE_2(@Payload ItemSoldOutConfirmed itemSoldOutConfirmed) {
        try {
            if (!itemSoldOutConfirmed.validate()) return;
                // view 객체 조회
            Optional<MyPage> myPageOptional = myPageRepository.findById(itemSoldOutConfirmed.getItemId());

            if( myPageOptional.isPresent()) {
                 MyPage myPage = myPageOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                 myPage.setItemStatus(itemSoldOutConfirmed.getStatus());
                // view 레파지 토리에 save
                 myPageRepository.save(myPage);
                }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenReservationCreated_then_UPDATE_3(@Payload ReservationCreated reservationCreated) {
        try {
            if (!reservationCreated.validate()) return;
                // view 객체 조회
            Optional<MyPage> myPageOptional = myPageRepository.findById(reservationCreated.getItemId());

            if( myPageOptional.isPresent()) {
                 MyPage myPage = myPageOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                 myPage.setRsvId(reservationCreated.getRsvId());
                 myPage.setRsvStatus(reservationCreated.getRsvStatus());
                // view 레파지 토리에 save
                 myPageRepository.save(myPage);
                }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenReservationConfirmed_then_UPDATE_4(@Payload ReservationConfirmed reservationConfirmed) {
        try {
            if (!reservationConfirmed.validate()) return;
                // view 객체 조회
            Optional<MyPage> myPageOptional = myPageRepository.findById(reservationConfirmed.getItemId());

            if( myPageOptional.isPresent()) {
                 MyPage myPage = myPageOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                 myPage.setRsvStatus(reservationConfirmed.getRsvStatus());
                // view 레파지 토리에 save
                 myPageRepository.save(myPage);
                }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenReservationCompleted_then_UPDATE_5(@Payload ReservationCompleted reservationCompleted) {
        try {
            if (!reservationCompleted.validate()) return;
                // view 객체 조회
            Optional<MyPage> myPageOptional = myPageRepository.findById(reservationCompleted.getItemId());

            if( myPageOptional.isPresent()) {
                 MyPage myPage = myPageOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                 myPage.setRsvStatus(reservationCompleted.getRsvStatus());
                // view 레파지 토리에 save
                 myPageRepository.save(myPage);
                }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenItemReserved_then_UPDATE_6(@Payload ItemReserved itemReserved) {
        try {
            if (!itemReserved.validate()) return;
                // view 객체 조회
            Optional<MyPage> myPageOptional = myPageRepository.findById(itemReserved.getId());

            if( myPageOptional.isPresent()) {
                 MyPage myPage = myPageOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                 myPage.setItemStatus(itemReserved.getStatus());
                // view 레파지 토리에 save
                 myPageRepository.save(myPage);
                }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenReservationDeleted_then_UPDATE_7(@Payload ReservationDeleted reservationDeleted) {
        try {
            if (!reservationDeleted.validate()) return;
                // view 객체 조회

                    List<MyPage> myPageList = myPageRepository.findByRsvId(reservationDeleted.getItemId());
                    for(MyPage myPage : myPageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myPage.setRsvStatus(reservationDeleted.getRsvStatus());
                // view 레파지 토리에 save
                myPageRepository.save(myPage);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenReservationCompleteCacelled_then_UPDATE_8(@Payload ReservationCompleteCacelled reservationCompleteCacelled) {
        try {
            if (!reservationCompleteCacelled.validate()) return;
                // view 객체 조회
            Optional<MyPage> myPageOptional = myPageRepository.findById(reservationCompleteCacelled.getItemId());

            if( myPageOptional.isPresent()) {
                 MyPage myPage = myPageOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                 myPage.setRsvStatus(reservationCompleteCacelled.getRsvStatus());
                // view 레파지 토리에 save
                 myPageRepository.save(myPage);
                }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenItemAvaliableConfirmed_then_UPDATE_9(@Payload ItemAvaliableConfirmed itemAvaliableConfirmed) {
        try {
            if (!itemAvaliableConfirmed.validate()) return;
                // view 객체 조회
            Optional<MyPage> myPageOptional = myPageRepository.findById(itemAvaliableConfirmed.getId());

            if( myPageOptional.isPresent()) {
                 MyPage myPage = myPageOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                 myPage.setItemStatus(itemAvaliableConfirmed.getStatus());
                // view 레파지 토리에 save
                 myPageRepository.save(myPage);
                }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaymentApproved_then_UPDATE_10(@Payload PaymentApproved paymentApproved) {
        try {
            if (!paymentApproved.validate()) return;
                // view 객체 조회
            Optional<MyPage> myPageOptional = myPageRepository.findById(paymentApproved.getItemId());

            if( myPageOptional.isPresent()) {
                 MyPage myPage = myPageOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                 myPage.setPayId(paymentApproved.getPayId());
                 myPage.setPayStatus(paymentApproved.getStatus());
                // view 레파지 토리에 save
                 myPageRepository.save(myPage);
                }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaymentCancelled_then_UPDATE_11(@Payload PaymentCancelled paymentCancelled) {
        try {
            if (!paymentCancelled.validate()) return;
                // view 객체 조회
            Optional<MyPage> myPageOptional = myPageRepository.findById(paymentCancelled.getItemId());

            if( myPageOptional.isPresent()) {
                 MyPage myPage = myPageOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                 myPage.setPayStatus(paymentCancelled.getStatus());
                // view 레파지 토리에 save
                 myPageRepository.save(myPage);
                }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenItemDeleted_then_DELETE_1(@Payload ItemDeleted itemDeleted) {
        try {
            if (!itemDeleted.validate()) return;
            // view 레파지 토리에 삭제 쿼리
            myPageRepository.deleteById(itemDeleted.getItemId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

