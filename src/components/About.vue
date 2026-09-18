<script setup>
import { useI18n } from "vue-i18n";
const { t } = useI18n();
</script>

<template>
  <div class="about-section">
    <div class="container">

      <div class="section-header" data-aos="fade-up" data-aos-duration="600">
        <span class="section-eyebrow">{{ t('about.eyebrow') }}</span>
        <h2 class="section-title">{{ t('about.title') }}</h2>
        <div class="divider"></div>
      </div>

      <div class="about-grid" data-aos="fade-up" data-aos-duration="700">
        <div class="about-text">
          <p>{{ t('about.p1') }}</p>
          <p>{{ t('about.p2') }}</p>

          <div class="stats-row">
            <div class="stat">
              <span class="stat-num">2023</span>
              <span class="stat-label">{{ t('about.stat1_label') }}</span>
            </div>
            <div class="stat">
              <span class="stat-num">{{ t('about.stat2_value') }}</span>
              <span class="stat-label">{{ t('about.stat2_label') }}</span>
            </div>
            <div class="stat">
              <span class="stat-num">3</span>
              <span class="stat-label">{{ t('about.stat3_label') }}</span>
            </div>
          </div>
        </div>
      </div>

      <!-- Charts -->
      <div class="chart-row" ref="graphRef" data-aos="fade-up" data-aos-duration="800">
        <div class="chart-card">
          <div class="chart-item">
            <canvas id="passion"></canvas>
          </div>
          <p class="chart-label">{{ t('about.chart1') }}</p>
        </div>
        <div class="chart-card">
          <div class="chart-item">
            <canvas id="experience"></canvas>
          </div>
          <p class="chart-label">{{ t('about.chart2') }}</p>
        </div>
        <div class="chart-card">
          <div class="chart-item">
            <canvas id="success"></canvas>
          </div>
          <p class="chart-label">{{ t('about.chart3') }}</p>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
import Chart from "chart.js/auto";
import { nextTick } from "vue";
import AOS from "aos";

export default {
  mounted() {
    AOS.init();
    nextTick(() => {
      const target = this.$refs.graphRef;
      if (target) {
        const observer = new IntersectionObserver(
          (entries) => {
            entries.forEach((entry) => {
              if (entry.isIntersecting) {
                this.createChart("passion");
                this.createChart("experience");
                this.createChart("success");
                observer.unobserve(entry.target);
              }
            });
          },
          { threshold: 0.2 }
        );
        observer.observe(target);
      }
    });
  },
  methods: {
    createChart(elementId) {
      new Chart(elementId, {
        type: "doughnut",
        data: {
          datasets: [
            {
              data: [1],
              backgroundColor: ["#c9a84c"],
              borderColor: ["#c9a84c"],
              borderWidth: 5,
            },
          ],
        },
        plugins: [
          {
            id: "centerText",
            afterDraw(chart) {
              const { chartArea, ctx } = chart;
              if (!chartArea) return;
              ctx.save();
              ctx.font = "bold 18px Inter, sans-serif";
              ctx.textBaseline = "middle";
              ctx.textAlign = "center";
              ctx.fillStyle = "#1a1a2e";
              const centerX = (chartArea.left + chartArea.right) / 2;
              const centerY = (chartArea.top + chartArea.bottom) / 2;
              ctx.fillText("%100", centerX, centerY);
              ctx.restore();
            },
          },
        ],
        options: {
          maintainAspectRatio: true,
          aspectRatio: 1,
          cutout: "65%",
          events: [],
          plugins: {
            title: { display: false },
          },
        },
      });
    },
  },
};
</script>

<style scoped>
.about-section {
  padding: 120px 0;
  background: var(--bg-white);
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 40px;
}

.section-header {
  margin-bottom: 64px;
}

.section-eyebrow {
  display: inline-block;
  font-size: 12px;
  font-weight: 700;
  letter-spacing: 0.2em;
  text-transform: uppercase;
  color: var(--accent);
  margin-bottom: 12px;
}

.section-title {
  font-size: clamp(32px, 4vw, 52px);
  font-weight: 700;
  color: var(--text-dark);
  letter-spacing: -0.02em;
}

.divider {
  width: 56px;
  height: 3px;
  background: var(--accent);
  margin-top: 20px;
}

.about-grid {
  display: grid;
  grid-template-columns: 1fr;
  align-items: center;
  margin-bottom: 80px;
}

.about-text p {
  font-size: 15px;
  color: var(--text-mid);
  line-height: 1.9;
  margin-bottom: 18px;
}

.stats-row {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 24px;
  margin-top: 48px;
  padding-top: 36px;
  border-top: 1px solid var(--border);
}

.stat {
  display: flex;
  flex-direction: column;
  gap: 4px;
}

.stat-num {
  font-size: 36px;
  font-weight: 800;
  color: var(--text-dark);
  letter-spacing: -0.03em;
  line-height: 1;
}

.stat-label {
  font-size: 11px;
  font-weight: 600;
  letter-spacing: 0.07em;
  text-transform: uppercase;
  color: var(--text-light);
}

/* Charts */
.chart-row {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 32px;
}

.chart-card {
  background: transparent;
  padding: 8px 24px 24px;
  text-align: center;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.chart-item {
  width: 100%;
  max-width: 180px;
}

.chart-label {
  font-size: 13px;
  font-weight: 700;
  letter-spacing: 0.12em;
  text-transform: uppercase;
  color: var(--text-dark);
  margin-top: 16px;
}

/* Tablet */
@media (max-width: 900px) {
  .about-section { padding: 80px 0; }
  .container { padding: 0 24px; }
  .about-grid { gap: 40px; margin-bottom: 56px; }
  .chart-row { gap: 20px; }
}

/* Mobile */
@media (max-width: 600px) {
  .about-section { padding: 64px 0; }
  .container { padding: 0 18px; }
  .section-header { margin-bottom: 40px; }
  .about-grid { gap: 32px; margin-bottom: 48px; }
  .about-text p { font-size: 14px; }
  .stats-row { gap: 16px; margin-top: 36px; padding-top: 28px; }
  .stat-num { font-size: 26px; }
  .stat-label { font-size: 10px; }

  .chart-row {
    grid-template-columns: 1fr;
    gap: 16px;
    max-width: 240px;
    margin: 0 auto;
  }

  .chart-card { padding: 24px 20px 16px; }
  .chart-item { max-width: 160px; }
}

@media (max-width: 400px) {
  .stat-num { font-size: 22px; }
  .stats-row { gap: 10px; }
}
</style>
