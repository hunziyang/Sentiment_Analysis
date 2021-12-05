import request from '@/utils/request'

export function create (params) {
  return request({
    url: '/tickets',
    method: 'post',
    data: params
  })
}
