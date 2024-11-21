<template>
  <div class="portfolio">
    <div
      class="title-decorative-2 wow slideInUp animated"
      data-aos="fade-up"
      data-aos-duration="650"
      style="visibility: visible; animation-delay: 0s"
    >
      PROJELER
    </div>

    <div class="gallery">
      <div class="section">
        <div
          class="first pic"
          data-aos="fade-right"
          data-aos-easing="ease-in-sine"
          data-aos-duration="650"
          data-aos-anchor-placement="center-center"
        >
          <img src="/bursa-fibabank/IMG_4537.PNG" alt="" />
          <div class="side-note">
            <div class="container">
              <h3 class="d-flex justify-space-between">
                BURSA FİBABANK
                <div class="icon">
                  <v-btn
                    variant="text"
                    icon
                    @click="setItemsForCarousel(bursa)"
                  >
                    <v-icon>fa-solid fa-magnifying-glass-plus</v-icon>
                  </v-btn>
                </div>
              </h3>
            </div>
          </div>
        </div>

        <div
          class="second pic"
          data-aos="fade-left"
          data-aos-easing="ease-in-sine"
          data-aos-duration="650"
        >
          <img src="\dhl-kayseri\IMG_4545.PNG" alt="" />
          <div class="side-note">
            <div class="container">
              <h3 class="d-flex justify-space-between">
                DHL KAYSERİ
                <div class="icon" @click="setItemsForCarousel(kayseri)">
                  <v-btn variant="text" icon>
                    <v-icon>fa-solid fa-magnifying-glass-plus</v-icon>
                  </v-btn>
                </div>
              </h3>
            </div>
          </div>
        </div>
      </div>

      <div class="section">
        <div
          class="first pic"
          data-aos="fade-right"
          data-aos-easing="ease-in-sine"
          data-aos-duration="650"
        >
          <img src="\dhl-denizli\d1.jpeg" alt="" />
          <div class="side-note">
            <div class="container">
              <h3 class="d-flex justify-space-between">
                DHL DENİZLİ
                <div class="icon">
                  <v-btn
                    variant="text"
                    icon
                    @click="setItemsForCarousel(denizli)"
                  >
                    <v-icon>fa-solid fa-magnifying-glass-plus</v-icon>
                  </v-btn>
                </div>
              </h3>
            </div>
          </div>
        </div>
        <div
          class="second pic"
          data-aos="fade-left"
          data-aos-easing="ease-in-sine"
          data-aos-duration="650"
        >
          <img src="/dhl-izmir/i6.jpeg" alt="" />
          <div class="side-note">
            <div class="container">
              <h3 class="d-flex justify-space-between">
                DHL İZMİR
                <div class="icon">
                  <v-btn
                    variant="text"
                    icon
                    @click="setItemsForCarousel(izmir)"
                  >
                    <v-icon>fa-solid fa-magnifying-glass-plus</v-icon>
                  </v-btn>
                </div>
              </h3>
            </div>
          </div>
        </div>
      </div>
    </div>

    <v-dialog v-model="dialog" :width="dialogWidth" fullscreen>
      <div class="d-flex justify-end">
        <v-btn variant="text" icon @click="dialog = !dialog"><v-icon color="white">fa fa-close</v-icon></v-btn>
      </div>
      <v-col
        class="d-flex flex-wrap justify-center align-center"
        style="height: 100vh"
      >
        <v-carousel hide-delimiters>
          <template v-slot:prev="{ props }">
            <v-btn
              class="rounded-0"
              color="rgb(56, 62, 66)"
              variant="elevated"
              @click="props.onClick"
              ><v-icon>fa-solid fa-arrow-left</v-icon></v-btn
            >
          </template>
          <template v-slot:next="{ props }">
            <v-btn
              class="rounded-0"
              color="rgb(56, 62, 66)"
              variant="elevated"
              @click="props.onClick"
              ><v-icon>fa-solid fa-arrow-right</v-icon></v-btn
            >
          </template>
          <v-carousel-item
            v-for="(item, index) in items"
            :key="index"
            :src="item.img"
          ></v-carousel-item>
        </v-carousel>
      </v-col>
    </v-dialog>
  </div>
</template>

<script>
import "animate.css";
import AOS from "aos";
import CustomCarousel from "./CustomCarousel.vue";
export default {
  mounted() {
    AOS.init();
  },
  components: { CustomCarousel },
  data: function () {
    return {
      dialog: false,
      dialogWidth: "100%",
      bursa: [
        {
          header: "BURSA FİBABANK",
          img: "../bursa-fibabank/IMG_4537.PNG",
        },
        {
          header: "BURSA FİBABANK",
          img: "../bursa-fibabank/IMG_4538.PNG",
        },
        {
          header: "BURSA FİBABANK",
          img: "../bursa-fibabank/IMG_4539.PNG",
        },
        {
          header: "BURSA FİBABANK",
          img: "../bursa-fibabank/IMG_4540.PNG",
        },
        {
          header: "BURSA FİBABANK",
          img: "../bursa-fibabank/IMG_4541.PNG",
        },
        {
          header: "BURSA FİBABANK",
          img: "../bursa-fibabank/IMG_4542.PNG",
        },
      ],
      denizli: [
        {
          header: "DENİZLİ DHL",
          img: "../bursa-fibabank/IMG_4537.PNG",
        },
        {
          header: "DENİZLİ DHL",
          img: "../bursa-fibabank/IMG_4538.PNG",
        },
        {
          header: "DENİZLİ DHL",
          img: "../bursa-fibabank/IMG_4539.PNG",
        },
        {
          header: "DENİZLİ DHL",
          img: "../bursa-fibabank/IMG_4540.PNG",
        },
        {
          header: "DENİZLİ DHL",
          img: "../bursa-fibabank/IMG_4541.PNG",
        },
        {
          header: "DENİZLİ DHL",
          img: "../bursa-fibabank/IMG_4542.PNG",
        },
      ],
      izmir: [
        {
          header: "İZMİR DHL",
          img: "../dhl-izmir/i1.jpeg",
        },
        {
          header: "İZMİR DHL",
          img: "../dhl-izmir/i2.jpeg",
        },
        {
          header: "İZMİR DHL",
          img: "../dhl-izmir/i3.jpeg",
        },
        {
          header: "İZMİR DHL",
          img: "../dhl-izmir/i4.jpeg",
        },
        {
          header: "İZMİR DHL",
          img: "../dhl-izmir/i5.jpeg",
        },
        {
          header: "İZMİR DHL",
          img: "../dhl-izmir/i6.jpeg",
        },
      ],
      kayseri: [
        {
          header: "KAYSERİ DHL",
          img: "../dhl-kayseri/IMG_4543.PNG",
        },
        {
          header: "KAYSERİ DHL",
          img: "../dhl-kayseri/IMG_4544.PNG",
        },
        {
          header: "KAYSERİ DHL",
          img: "../dhl-kayseri/IMG_4545.PNG",
        },
        {
          header: "KAYSERİ DHL",
          img: "../dhl-kayseri/IMG_4546.PNG",
        },
        {
          header: "KAYSERİ DHL",
          img: "../dhl-kayseri/IMG_4547.PNG",
        },
        {
          header: "KAYSERİ DHL",
          img: "../dhl-kayseri/IMG_4548.PNG",
        },
      ],
      items: [],
    };
  },
  methods: {
    setItemsForCarousel(items) {
      this.items = items;
      this.dialog = true;
    },
  },
};
</script>

<style scoped>
.portfolio {
  width: 80%;
  margin: auto;
  padding-bottom: 10%;
  color: #2c3e50;
}
.title-decorative-2 {
  line-height: 1.3em;
  font-size: 250px;
  text-align: left;
  font-weight: 650;
  color: rgba(234, 234, 247, 0.7);
  display: block;
}

.gallery {
  width: 100%;
  margin: auto 3%;
}
.section {
  display: flex;
  justify-content: space-around;
  margin-bottom: 250px;
  height: 500px;
}

.first,
.second {
  width: 45%;
  position: relative;
  object-fit: cover;
}

.second {
  margin-top: -100px;
}

.section img {
  /* display: inline-block; */
  width: 100%;
  height: 650px;
  object-fit: cover;
  filter: brightness(0.7);
  transition: 1s ease-in-out;
}
.section img:hover {
  filter: brightness(0.9);
}
.side-note {
  transition: 1s ease-in-out;
}
.pic:hover .side-note {
  height: 100px;
  width: 60%;
  margin-top: -100px;
  background-color: #dcdce4;
  display: block;
  transition: 1s ease-in-out;
}

.container {
  display: none;
  padding: 20px;
}
.pic:hover .container {
  display: block;
  padding: 20px;
}

.pic:hover .side-note h3 {
  font-size: 25px;
  font-weight: 600;
  font-style: italic;
}

@media screen and (max-width: 420px) {
  .portfolio {
    width: 100%;
    overflow-x: hidden;
  }

  .title-decorative-2 {
    width: 80%;
    font-size: 70px;
    font-weight: 600;
    margin: auto auto;
    margin-left: 10px;
  }

  .gallery {
    width: 100%;
    margin: auto auto;
  }
  .section {
    display: block;
    width: 100%;
    margin-top: 100px;
    margin-bottom: 100px;
    height: 900px;
  }

  .first,
  .second {
    width: 90%;
    margin: auto;
    padding-bottom: 100px;
  }

  .second {
    margin-bottom: -80px;
  }

  .section img {
    height: 350px;
  }
  .side-note {
    height: 70px;
    width: 60%;
    margin-top: -45px;
    background-color: #dcdce4;
    display: block;
    transition: 1s ease-in-out;
  }

  .container {
    display: block;
    padding: 10px;
  }
  .container h3 {
    font-size: 15px !important;
    font-weight: 600;
  }
}
</style>