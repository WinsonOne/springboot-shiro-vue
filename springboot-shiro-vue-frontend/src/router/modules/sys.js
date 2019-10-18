/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const sysRouter = {
  path: '/sys',
  component: Layout,
  redirect: 'noRedirect',
  name: 'SystemManagement',
  meta: {
    title: '系统管理',
    icon: 'component'
  },
  children: [
    {
      path: 'user',
      component: () => import('@/views/sys/user'),
      name: 'SysUser',
      meta: { title: '用户管理' }
    },
    {
      path: 'role',
      component: () => import('@/views/sys/role'),
      name: 'SysRole',
      meta: { title: '角色管理' }
    },
    {
      path: 'resource',
      component: () => import('@/views/sys/resource'),
      name: 'SysResource',
      meta: { title: '资源管理' }
    }
  ]
}

export default sysRouter
