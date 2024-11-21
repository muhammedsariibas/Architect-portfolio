<template >
  <div class="about" data-aos="fade-up" data-aos-duration="600">
    <h2 class="title-decorative">
      <span class="title-decorative-letter">HAKKIMIZDA</span>
    </h2>

    <div class="first">
      <h3>Hakkımızda</h3>
      <p>
        Firmamız, 2023 yılında 3 ortak tarafından kurulmuş olup teknoloji,
        mühendislik, üretim, lojistik ve hizmet sektörlerinde faaliyet
        göstermektedir. İnşaat taahhüt, elektrik, mekanik ve mimari alanlarda
        anahtar teslim projeler hazırlayıp, takip etmektedir. İnovasyon
        süreçlerine büyük önem veren firmamız, bu süreçleri sürekli geliştirerek
        genel stratejimize uyumlu hale getirmektedir. Aile şirketi olmanın
        esnekliğini ve hızlı karar alma yeteneğini profesyonel iş anlayışıyla
        birleştirerek, uzman ekibimizle kaliteli hizmet sunmayı ve Türkiye'nin
        büyük şirketlerinden biri olmayı hedefliyoruz.
      </p>
      <img src="../assets/pic.jpg" alt="" />
    </div>

    <div class="second" data-aos="fade-up" ref="graph" data-aos-duration="800">
      <div class="section">
        <v-col
          cols="12"
          sm="12"
          md="12"
          class="px-0 py-0 d-flex justify-center align-center"
          style="height: 200px"
        >
          <canvas id="passion"></canvas>
        </v-col>
      </div>

      <div class="section">
        <v-col
          cols="12"
          sm="12"
          md="12"
          class="px-0 py-0 d-flex justify-center align-center"
          style="height: 200px"
        >
          <canvas id="experience"></canvas>
        </v-col>
      </div>
      <div class="section">
        <v-col
          cols="12"
          sm="12"
          md="12"
          class="px-0 py-0 d-flex justify-center align-center"
          style="height: 200px"
        >
          <canvas id="success"></canvas>
        </v-col>
      </div>
    </div>
  </div>
</template>

<script>
import Chart from "chart.js/auto";
import "animate.css";
import { nextTick } from "vue";
import AOS from "aos";
export default {
  mounted() {
    let _this = this;

    AOS.init();

    nextTick(() => {
      const target = this.$refs.graph;
      if (target) {
        const observer = new IntersectionObserver((entries) => {
          entries.forEach((entry) => {
            if (entry.isIntersecting) {
              _this.createChart("TUTKU", "passion");
              _this.createChart("DENEYİM", "experience");
              _this.createChart("BAŞARI", "success"); // Element görünür olduğunda bu fonksiyon çalışır
              observer.unobserve(entry.target); // İsteğe bağlı: bir kez gözlemlendikten sonra gözlemeyi kaldır
            }
          });
        });
        observer.observe(target);
      }
    });
  },
  methods: {
    createChart(text, elementId) {
      const passion = document.getElementById(elementId);
      new Chart(elementId, {
        type: "doughnut",
        data: {
          datasets: [
            {
              data: [1],
              backgroundColor: ["rgb(44, 62, 80)"],
              borderColor: ["rgb(44, 62, 80)"], // Çerçevenin rengi siyah
              borderWidth: 7,
            },
          ],
        },
        plugins: [
          {
            id: "text",
            afterDraw: function (chart, a, b) {
              let width = chart.width,
                height = chart.height,
                ctx = chart.ctx;

              ctx.restore();

              ctx.font = `bold 30px Segoe UI`;
              ctx.weight = 700;
              ctx.textBaseline = "middle";

              let text = "%100",
                textX = Math.round((width - ctx.measureText(text).width) / 2),
                textY = height / 2.3;
              ctx.fillStyle = "rgb(44, 62, 80)";
              ctx.fillText(text, textX, textY);
              ctx.save();
            },
          },
        ],
        options: {
          maintainAspectRatio: false, // Genişlik ve yükseklik oranının korunmasını devre dışı bırakır
          aspectRatio: 6,
          plugins: {
            title: {
              display: true,
              text: text,
              font: {
                size: 20, // Alt başlık yazı tipi boyutu,
                weight: 700,
                family: "Segoe UI",
              },
              color: "rgb(44, 62, 80)",
              position: "bottom",
            },
          },

          cutout: 65,
          radius: 65,
          events: [],
        },
      });
    },
  },
};
</script>

<style scoped>
/* element.style {
  visibility: visible;
  animation-delay: 0s;
}
.animated {
  animation-duration: 1s;
  animation-fill-mode: both;
  opacity: 1;
}

.title-decorative-2 {
  line-height: 1.3em;
  font-weight: 700;
  color: #efeff0;
  font-size: 200px;
  text-align: left;
  display: block;
} */

.title-decorative {
  padding-top: 50px;
}
h2,
.heading-2 {
  font-size: 80px;
}

.title-decorative-letter {
  position: absolute;
  left: 0.5em;
  top: -70px;
  font-size: 210px;
  line-height: 1;
  -webkit-text-stroke: 0.7px rgba(15, 15, 18, 0.2);
  color: transparent;
}
.title-decorative {
  position: relative;
  text-transform: uppercase;
}

.about {
  display: flex;
  margin: auto;
  margin-top: 10%;
  padding-bottom: 10%;
  width: 80%;
  justify-content: space-between;
  align-content: center;
  color: #2c3e50;
}

.background-text {
  font-size: 100px;
  z-index: 1;
}

.first {
  width: 40%;
  margin-top: 3%;
  z-index: 100;
}

.first img {
  margin-top: 30px;
  width: 450px;
}
.first p {
  margin-top: 30px;
  font-size: 14px;
}

.first h3 {
  font-size: 55px;
  text-align: right;
}

.second {
  width: 40%;
  margin-top: 10%;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.second h2 {
  font-size: 80px;
  font-weight: 700;
  letter-spacing: 5px;
}

.second h3 {
  font-size: 20px;
  font-weight: 600;
  margin-top: -15px;
}

.details {
  padding-bottom: 70px;
}

@media screen and (max-width: 420px) {
  .about {
    display: block;
    width: 100%;
    margin-top: 100px;
    overflow-x: hidden;
  }

  .title-decorative-letter {
    position: absolute;
    left: 0;
    top: 0;
    font-size: 120px;
  }

  .first {
    width: 90%;
    margin: auto auto;
  }
  .second {
    width: 80%;
    margin: auto auto;
  }
  .first h3 {
    font-size: 40px;
    text-align: right;
    width: 100%;
  }
  .first img {
    width: 90%;
  }

  .second {
    margin-top: 50px;
  }

  .second h2 {
    font-size: 60px;
    font-weight: 700;
    letter-spacing: 3px;
  }

  .second h3 {
    font-size: 13px;

    font-weight: 400;
    margin-top: -10px;
  }
}
</style> 