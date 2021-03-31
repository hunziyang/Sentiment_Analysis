import request from '@/utils/request'

export function all () {
  return request({
    url: '/student/insert',
    method: 'get'
  })
}
