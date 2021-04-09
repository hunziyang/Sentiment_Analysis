import request from '@/utils/request'

export function search(params) {
    return request({
        url: '/ticket/search',
        method: 'post',
        data: params
    })
}
export function seeFile(params) {
    return request({
        url: '/ticket/seeFile/'+params,
        method: 'post'
    })
}
