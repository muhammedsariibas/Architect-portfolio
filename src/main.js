import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import SvgIcon from "vue3-icon";

//vue-app/src/main.js
import { library } from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { fas } from '@fortawesome/free-solid-svg-icons'
library.add(fas);
import { fab } from '@fortawesome/free-brands-svg-icons';
library.add(fab);
import { far } from '@fortawesome/free-regular-svg-icons';
library.add(far);
import { dom } from "@fortawesome/fontawesome-svg-core";
dom.watch();


// Vuetify
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'

// Components

const vuetify = createVuetify({
  components,
  directives,
})


// importing AOS css style globally
import 'aos/dist/aos.css'

const app = createApp(App)
app.component("font-awesome-icon", FontAwesomeIcon);
app.use(router)
app.use(vuetify)

app.mount('#app')
