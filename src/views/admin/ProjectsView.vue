<script setup>
import AdminLayout from '@/components/admin/AdminLayout.vue'
import { ref, onMounted } from 'vue'
import { getProjects, createProject, updateProject, deleteProject, uploadProjectImage, removeProjectImage } from '@/api/projects'

const projects = ref([])
const loading = ref(true)
const saving = ref(false)
const error = ref('')
const success = ref('')
const editing = ref(null)
const form = ref({ title: '', description: '', category: '' })

onMounted(load)
async function load() {
  loading.value = true
  try { projects.value = (await getProjects()).data } catch { error.value = 'Projeler yüklenemedi.' } finally { loading.value = false }
}
function startCreate() { editing.value = 'new'; form.value = { title: '', description: '', category: '' } }
function startEdit(project) { editing.value = project.id; form.value = { title: project.title, description: project.description || '', category: project.category || '' } }
function cancelEdit() { editing.value = null }
async function saveProject() {
  saving.value = true; error.value = ''; success.value = ''
  try {
    if (editing.value === 'new') await createProject(form.value)
    else await updateProject(editing.value, form.value)
    editing.value = null; success.value = 'Proje kaydedildi.'; await load()
  } catch (e) { error.value = e.response?.data?.message || 'Proje kaydedilemedi.' } finally { saving.value = false }
}
async function removeProject(project) {
  if (!window.confirm(`“${project.title}” projesi silinsin mi? Görseller silinmez, medya arşivinde kalır.`)) return
  try { await deleteProject(project.id); projects.value = projects.value.filter((item) => item.id !== project.id); success.value = 'Proje silindi.' } catch { error.value = 'Proje silinemedi.' }
}
async function addImages(project, event) {
  const files = [...event.target.files]
  if (!files.length) return
  error.value = ''; success.value = ''
  try {
    for (const file of files) {
      const data = new FormData(); data.append('file', file); data.append('category', 'GALLERY'); data.append('projectId', project.id)
      await uploadProjectImage(data)
    }
    success.value = `${files.length} görsel eklendi.`; await load()
  } catch (e) { error.value = e.response?.data?.message || 'Görseller yüklenemedi.' }
  event.target.value = ''
}
async function detachImage(project, image) {
  if (!window.confirm('Bu görsel projeden kaldırılsın mı?')) return
  try { await removeProjectImage(project.id, image.id); project.images = project.images.filter((item) => item.id !== image.id); success.value = 'Görsel projeden kaldırıldı.' } catch { error.value = 'Görsel kaldırılamadı.' }
}
</script>

<template>
  <AdminLayout>
    <div class="projects-page">
      <div class="page-heading"><div><p class="eyebrow">İçerik yönetimi</p><h1>Projeler</h1><p class="intro">Her projenin başlığını, açıklamasını ve galerisini ayrı ayrı yönetin.</p></div><button class="primary" type="button" @click="startCreate"><i class="fa-solid fa-plus"></i> Yeni proje</button></div>
      <div v-if="success" class="alert success" role="status" aria-live="polite">{{ success }}</div>
      <div v-if="error" class="alert error" role="alert">{{ error }}</div>

      <form v-if="editing !== null" class="editor-card" @submit.prevent="saveProject">
        <div class="editor-heading"><h2>{{ editing === 'new' ? 'Yeni proje' : 'Projeyi düzenle' }}</h2><button type="button" class="icon-button" aria-label="Düzenlemeyi kapat" @click="cancelEdit"><i class="fa-solid fa-xmark"></i></button></div>
        <div class="form-grid">
          <label>Proje adı *<input v-model.trim="form.title" required maxlength="120" /></label>
          <label>Kategori<input v-model.trim="form.category" placeholder="Ofis Tasarımı" maxlength="80" /></label>
          <label class="full">Açıklama<textarea v-model.trim="form.description" rows="4" maxlength="1000" placeholder="Projenin kapsamını ve öne çıkan özelliklerini anlatın."></textarea></label>
        </div>
        <div class="editor-actions"><button type="button" class="secondary" @click="cancelEdit">Vazgeç</button><button class="primary" type="submit" :disabled="saving">{{ saving ? 'Kaydediliyor…' : 'Kaydet' }}</button></div>
      </form>

      <div v-if="loading" class="state"><i class="fa-solid fa-spinner fa-spin"></i> Projeler yükleniyor…</div>
      <div v-else-if="!projects.length" class="state empty"><i class="fa-regular fa-folder-open"></i><strong>Henüz proje yok</strong><span>İlk projenizi ekleyerek başlayın.</span></div>
      <div v-else class="project-admin-grid">
        <article v-for="project in projects" :key="project.id" class="project-card">
          <div class="project-card-head"><div><h2>{{ project.title }}</h2><span v-if="project.category" class="category">{{ project.category }}</span></div><div class="card-actions"><button type="button" title="Düzenle" aria-label="Projeyi düzenle" @click="startEdit(project)"><i class="fa-solid fa-pen"></i></button><button type="button" title="Sil" aria-label="Projeyi sil" @click="removeProject(project)"><i class="fa-solid fa-trash"></i></button></div></div>
          <p v-if="project.description" class="description">{{ project.description }}</p>
          <div v-if="project.images?.length" class="image-grid"><div v-for="image in project.images" :key="image.id" class="image-item"><img :src="image.fileUrl" :alt="`${project.title} görseli`" loading="lazy"><button type="button" aria-label="Görseli projeden kaldır" title="Projeden kaldır" @click="detachImage(project, image)"><i class="fa-solid fa-xmark"></i></button></div></div><div v-else class="no-images">Bu projeye henüz görsel eklenmedi.</div>
          <label class="upload-button"><i class="fa-solid fa-images"></i> Görsel ekle<input type="file" accept="image/png,image/jpeg,image/webp" multiple @change="addImages(project, $event)"></label>
        </article>
      </div>
    </div>
  </AdminLayout>
</template>

<style scoped>
.projects-page { max-width: 1180px; }
.page-heading { display: flex; align-items: flex-start; justify-content: space-between; gap: 20px; margin-bottom: 28px; }
.eyebrow { margin: 0 0 6px; color: #c9a84c; font-size: 11px; font-weight: 700; letter-spacing: .14em; text-transform: uppercase; }
h1 { margin: 0 0 8px; color: #1a1a2e; font-size: 30px; } .intro { margin: 0; color: #718096; font-size: 14px; }
.primary,.secondary { border: 0; border-radius: 5px; padding: 11px 16px; cursor: pointer; font: inherit; font-weight: 700; }.primary { background: #1a1a2e; color: white; }.primary:hover { background: #c9a84c; color: #1a1a2e; }.primary:disabled { opacity: .6; cursor: wait; }.secondary { border: 1px solid #e2e8f0; background: white; color: #4a5568; }
.alert { margin-bottom: 18px; padding: 12px 16px; border-radius: 6px; font-size: 14px; }.success { background: #f0fff4; color: #276749; }.error { background: #fff5f5; color: #9b2c2c; }
.editor-card,.project-card { padding: 24px; background: white; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,.06); }.editor-card { margin-bottom: 24px; }.editor-heading,.project-card-head,.editor-actions { display: flex; align-items: center; justify-content: space-between; gap: 14px; }.editor-heading h2,.project-card h2 { margin: 0; color: #1a1a2e; font-size: 18px; }.icon-button,.card-actions button { border: 0; background: transparent; color: #718096; cursor: pointer; }.form-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 16px; margin: 22px 0; }.form-grid label { display: flex; flex-direction: column; gap: 7px; color: #4a5568; font-size: 12px; font-weight: 700; }.form-grid .full { grid-column: 1 / -1; }.form-grid input,.form-grid textarea { padding: 10px 12px; border: 1px solid #e2e8f0; border-radius: 4px; font: inherit; font-size: 14px; }.form-grid input:focus,.form-grid textarea:focus { outline: 2px solid #c9a84c; outline-offset: 1px; }.editor-actions { justify-content: flex-end; }
.project-admin-grid { display: grid; grid-template-columns: repeat(auto-fill,minmax(360px,1fr)); gap: 20px; }.project-card-head { align-items: flex-start; }.card-actions { display: flex; gap: 12px; }.card-actions button:hover { color: #c9a84c; }.card-actions button:last-child:hover { color: #e74c3c; }.category { display: inline-block; margin-top: 7px; color: #c9a84c; font-size: 11px; font-weight: 700; text-transform: uppercase; letter-spacing: .08em; }.description { color: #718096; font-size: 14px; line-height: 1.6; }.image-grid { display: grid; grid-template-columns: repeat(3,1fr); gap: 8px; margin: 18px 0; }.image-item { position: relative; aspect-ratio: 1; overflow: hidden; border-radius: 4px; background: #f4f5f7; }.image-item img { width: 100%; height: 100%; object-fit: cover; }.image-item button { position: absolute; top: 5px; right: 5px; width: 26px; height: 26px; border: 0; border-radius: 50%; background: rgba(26,26,46,.8); color: white; cursor: pointer; }.image-item button:hover { background: #e74c3c; }.no-images { margin: 18px 0; padding: 22px 10px; background: #f8f9fa; color: #a0aec0; text-align: center; font-size: 13px; }.upload-button { display: inline-flex; align-items: center; gap: 8px; padding: 10px 14px; border: 1px dashed #c9a84c; color: #8c722b; cursor: pointer; font-size: 13px; font-weight: 700; }.upload-button input { display: none; }.state { display: flex; flex-direction: column; align-items: center; gap: 10px; padding: 80px 20px; color: #a0aec0; }.state.empty i { font-size: 40px; color: #c9a84c; }.state strong { color: #4a5568; }
@media (max-width: 600px) { .page-heading { flex-direction: column; }.form-grid { grid-template-columns: 1fr; }.form-grid .full { grid-column: auto; }.project-admin-grid { grid-template-columns: 1fr; }.editor-card,.project-card { padding: 18px; } }
</style>
