<template>
<div>
    <el-container>
        <el-aside width="200px"><as></as></el-aside>
        <el-main>
            <router-view/>
        </el-main>
    </el-container>
</div>
</template>

<script>
    // import {all} from '@/api/homeApi'
    import aside from '@/components/Aside'
    export default {
        name: "task",
        data () {
            return {
            };
        },
        components:{
            'as':aside
        }
    }
</script>

<style scoped>

</style>
