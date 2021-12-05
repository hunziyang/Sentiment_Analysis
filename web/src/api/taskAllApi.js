import request from '@/utils/request'

export function search(params) {
    return request({
        url: '/tickets?pageNum='+params.pageNum+"&pageSize="+params.pageSize,
        method: 'get',
        data: params
    })
}
export function seeFile(params) {
    return request({
        url: '/see?id='+params,
        method: 'get'
    })
}
