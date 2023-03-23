<template>
  <h2>age : {{age}}</h2>
  <h2>name : {{name}}</h2>
  <button @click="changeInfo">Change Info</button>
</template>

<script>
  import {ref} from "./vue";
  export default {
    name: 'App',
    setup(){
      let name = ref('zhangsan')
      let age = ref(15)


      function changeInfo(){
        name.value = 'lisi'
        age.value = 23
      }

      return {
        name,
        age,
        changeInfo
      }
    }
  }
</script>