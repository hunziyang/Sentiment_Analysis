<template>
<div>
    <h1>wdy</h1>
    <el-radio v-model="radio" label="1">备选项</el-radio>
    <el-radio v-model="radio" label="2">备选项</el-radio>
</div>
</template>

<script>
    import {all} from '@/api/homeApi'
    export default {
        name: "task",
        data () {
            return {
                radio: '1'
            };
        },
        methods: {
          getAll () {
            all().then(result => result).catch(err => console.log(err))
          }
        },
        created () {
            this.getAll()
            console.log(process.env.VUE_APP_BASE_URL)
        }
    }
</script>

<style scoped>

</style>
