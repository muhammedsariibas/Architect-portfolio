import api from './axios'

export const getPublicProjects = () => api.get('/api/v1/projects/public')
export const getProjects = () => api.get('/api/v1/admin/projects')
export const createProject = (data) => api.post('/api/v1/admin/projects', data)
export const updateProject = (id, data) => api.put(`/api/v1/admin/projects/${id}`, data)
export const deleteProject = (id) => api.delete(`/api/v1/admin/projects/${id}`)
export const uploadProjectImage = (formData) => api.post('/api/v1/admin/media/upload', formData)
export const removeProjectImage = (projectId, mediaId) => api.delete(`/api/v1/admin/projects/${projectId}/images/${mediaId}`)
