<template>

<v-data-table
    :headers="headers"
    :items="myPage"
    :items-per-page="5"
    class="elevation-1"
  ></v-data-table>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'MyPage',
    props: {
      value: Object,
      editMode: Boolean,
      isNew: Boolean
    },
    data: () => ({
        headers: [
            { text: "itemId", value: "itemId" },
            { text: "itemName", value: "itemName" },
            { text: "itemStatus", value: "itemStatus" },
            { text: "rsvId", value: "rsvId" },
            { text: "rsvStatus", value: "rsvStatus" },
            { text: "payId", value: "payId" },
            { text: "payStatus", value: "payStatus" },
        ],
        myPage : [],
    }),
    async created() {
      var temp = await axios.get(axios.backend + '/mypages')

      this.myPage = temp.data._embedded.mypages;

    },
    methods: {
    }
  }
</script>

