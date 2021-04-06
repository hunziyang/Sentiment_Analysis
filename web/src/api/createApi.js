import request from '@/utils/request'

export function create (uid) {
  return request({
    url: '/ticket/create?uid='+uid,
    method: 'get'
  })
}
