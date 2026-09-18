import axios from 'axios'

const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL || 'http://localhost:8085',
  timeout: 15000,
})

api.interceptors.request.use((config) => {
  const token = localStorage.getItem('admin_token')
  const isAuthRequest = config.url?.includes('/auth/login') || config.url?.includes('/auth/refresh-token')
  if (token && !isAuthRequest) {
    config.headers = config.headers || {}
    config.headers.Authorization = `Bearer ${token}`
  }
  return config
})

api.interceptors.response.use(
  (res) => res,
  (err) => {
    const isAuthRequest = err.config?.url?.includes('/auth/login') || err.config?.url?.includes('/auth/refresh-token')
    if (err.response?.status === 401 && !isAuthRequest) {
      localStorage.removeItem('admin_token')
      const redirect = `${window.location.pathname}${window.location.search}${window.location.hash}`
      if (!window.location.pathname.startsWith('/admin/login')) {
        window.location.assign(`/admin/login?redirect=${encodeURIComponent(redirect)}`)
      }
    }
    return Promise.reject(err)
  }
)

export default api
