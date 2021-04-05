<template>
    <el-row class="tac">
        <el-col :span="24">
            <el-menu
                    default-active="$route.path"
                    class="el-menu-vertical-demo"
                    router>
                <el-submenu index="/task">
                    <template slot="title">
                        <i class="el-icon-location"></i>
                        <span>Sentiment_Analysis</span>
                    </template>
                    <el-menu-item index="/create">
                        <template slot="title">创建任务</template>
                    </el-menu-item>
                    <el-menu-item index="/my">
                        <template slot="title">我的任务</template>
                    </el-menu-item>
                </el-submenu>
            </el-menu>
        </el-col>
    </el-row>
</template>

<script>
    export default {
        name: "Aside"
    }
</script>

<style scoped>

</style>
