<template>
  <div class="slider-container">
    <div class="slider-content">
      <div class="slider-single" v-for="i,index in items" v-bind:key="index">
        <img
          class="slider-single-image"
          :src=i.img
          alt="1"
        />
        <h1 class="slider-single-title">Through the Mountains</h1>
        <a class="slider-single-likes" href="javascript:void(0);">
          <p>{{i.header}}</p>
        </a>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props: ['items'],
  mounted() {
    const repeat = false;
    const noArrows = false;
    const noBullets = false;

    const container = document.querySelector(".slider-container");
    var slide = document.querySelectorAll(".slider-single");
    var slideTotal = slide.length - 1;
    var slideCurrent = -1;

    function initBullets() {
      if (noBullets) {
        return;
      }
      const bulletContainer = document.createElement("div");
      bulletContainer.classList.add("bullet-container");
      slide.forEach((elem, i) => {
        const bullet = document.createElement("div");
        bullet.classList.add("bullet");
        bullet.id = `bullet-index-${i}`;
        bullet.addEventListener("click", () => {
          goToIndexSlide(i);
        });
        bulletContainer.appendChild(bullet);
        elem.classList.add("proactivede");
      });
      container.appendChild(bulletContainer);
    }

    function initArrows() {
      if (noArrows) {
        return;
      }
      const leftArrow = document.createElement("a");
      const iLeft = document.createElement("i");
      iLeft.classList.add("fa");
      iLeft.classList.add("fa-arrow-left");
      leftArrow.classList.add("slider-left");
      leftArrow.appendChild(iLeft);
      leftArrow.addEventListener("click", () => {
        slideLeft();
      });
      const rightArrow = document.createElement("a");
      const iRight = document.createElement("i");
      iRight.classList.add("fa");
      iRight.classList.add("fa-arrow-right");
      rightArrow.classList.add("slider-right");
      rightArrow.appendChild(iRight);
      rightArrow.addEventListener("click", () => {
        slideRight();
      });
      container.appendChild(leftArrow);
      container.appendChild(rightArrow);
    }

    function slideInitial() {
      initBullets();
      initArrows();
      setTimeout(function () {
        slideRight();
      }, 500);
    }

    function updateBullet() {
      if (!noBullets) {
        document
          .querySelector(".bullet-container")
          .querySelectorAll(".bullet")
          .forEach((elem, i) => {
            elem.classList.remove("active");
            if (i === slideCurrent) {
              elem.classList.add("active");
            }
          });
      }
      checkRepeat();
    }

    function checkRepeat() {
      if (!repeat) {
        if (slideCurrent === slide.length - 1) {
          slide[0].classList.add("not-visible");
          slide[slide.length - 1].classList.remove("not-visible");
          if (!noArrows) {
            document
              .querySelector(".slider-right")
              .classList.add("not-visible");
            document
              .querySelector(".slider-left")
              .classList.remove("not-visible");
          }
        } else if (slideCurrent === 0) {
          slide[slide.length - 1].classList.add("not-visible");
          slide[0].classList.remove("not-visible");
          if (!noArrows) {
            document.querySelector(".slider-left").classList.add("not-visible");
            document
              .querySelector(".slider-right")
              .classList.remove("not-visible");
          }
        } else {
          slide[slide.length - 1].classList.remove("not-visible");
          slide[0].classList.remove("not-visible");
          if (!noArrows) {
            document
              .querySelector(".slider-left")
              .classList.remove("not-visible");
            document
              .querySelector(".slider-right")
              .classList.remove("not-visible");
          }
        }
      }
    }

    function slideRight() {
      if (slideCurrent < slideTotal) {
        slideCurrent++;
      } else {
        slideCurrent = 0;
      }

      if (slideCurrent > 0) {
        var preactiveSlide = slide[slideCurrent - 1];
      } else {
        var preactiveSlide = slide[slideTotal];
      }
      var activeSlide = slide[slideCurrent];
      if (slideCurrent < slideTotal) {
        var proactiveSlide = slide[slideCurrent + 1];
      } else {
        var proactiveSlide = slide[0];
      }

      slide.forEach((elem) => {
        var thisSlide = elem;
        if (thisSlide.classList.contains("preactivede")) {
          thisSlide.classList.remove("preactivede");
          thisSlide.classList.remove("preactive");
          thisSlide.classList.remove("active");
          thisSlide.classList.remove("proactive");
          thisSlide.classList.add("proactivede");
        }
        if (thisSlide.classList.contains("preactive")) {
          thisSlide.classList.remove("preactive");
          thisSlide.classList.remove("active");
          thisSlide.classList.remove("proactive");
          thisSlide.classList.remove("proactivede");
          thisSlide.classList.add("preactivede");
        }
      });
      preactiveSlide.classList.remove("preactivede");
      preactiveSlide.classList.remove("active");
      preactiveSlide.classList.remove("proactive");
      preactiveSlide.classList.remove("proactivede");
      preactiveSlide.classList.add("preactive");

      activeSlide.classList.remove("preactivede");
      activeSlide.classList.remove("preactive");
      activeSlide.classList.remove("proactive");
      activeSlide.classList.remove("proactivede");
      activeSlide.classList.add("active");

      proactiveSlide.classList.remove("preactivede");
      proactiveSlide.classList.remove("preactive");
      proactiveSlide.classList.remove("active");
      proactiveSlide.classList.remove("proactivede");
      proactiveSlide.classList.add("proactive");

      updateBullet();
    }

    function slideLeft() {
      if (slideCurrent > 0) {
        slideCurrent--;
      } else {
        slideCurrent = slideTotal;
      }

      if (slideCurrent < slideTotal) {
        var proactiveSlide = slide[slideCurrent + 1];
      } else {
        var proactiveSlide = slide[0];
      }
      var activeSlide = slide[slideCurrent];
      if (slideCurrent > 0) {
        var preactiveSlide = slide[slideCurrent - 1];
      } else {
        var preactiveSlide = slide[slideTotal];
      }
      slide.forEach((elem) => {
        var thisSlide = elem;
        if (thisSlide.classList.contains("proactive")) {
          thisSlide.classList.remove("preactivede");
          thisSlide.classList.remove("preactive");
          thisSlide.classList.remove("active");
          thisSlide.classList.remove("proactive");
          thisSlide.classList.add("proactivede");
        }
        if (thisSlide.classList.contains("proactivede")) {
          thisSlide.classList.remove("preactive");
          thisSlide.classList.remove("active");
          thisSlide.classList.remove("proactive");
          thisSlide.classList.remove("proactivede");
          thisSlide.classList.add("preactivede");
        }
      });

      preactiveSlide.classList.remove("preactivede");
      preactiveSlide.classList.remove("active");
      preactiveSlide.classList.remove("proactive");
      preactiveSlide.classList.remove("proactivede");
      preactiveSlide.classList.add("preactive");

      activeSlide.classList.remove("preactivede");
      activeSlide.classList.remove("preactive");
      activeSlide.classList.remove("proactive");
      activeSlide.classList.remove("proactivede");
      activeSlide.classList.add("active");

      proactiveSlide.classList.remove("preactivede");
      proactiveSlide.classList.remove("preactive");
      proactiveSlide.classList.remove("active");
      proactiveSlide.classList.remove("proactivede");
      proactiveSlide.classList.add("proactive");

      updateBullet();
    }

    function goToIndexSlide(index) {
      const sliding =
        slideCurrent > index ? () => slideRight() : () => slideLeft();
      while (slideCurrent !== index) {
        sliding();
      }
    }

    slideInitial();
  },
};
</script>
<style lang="scss" >
$time: 500ms;
$delay: $time/2;
$mode: cubic-bezier(0.17, 0.67, 0.55, 1.43);
@keyframes heartbeat {
  0% {
    transform: scale(0);
  }
  25% {
    transform: scale(1.2);
  }
  50% {
    transform: scale(1);
  }
  75% {
    transform: scale(1.2);
  }
  100% {
    transform: scale(1);
  }
}

.slider-container {
  position: relative;
  margin: 0 auto;
  width: 800px;
  height: 600px;
  .bullet-container {
    position: absolute;
    bottom: 10px;
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    .bullet {
      margin-right: 14px;
      &:last-child {
        margin-right: 0px;
      }
      height: 20px;
      width: 20px;
      border-radius: 50%;
      background-color: white;
      opacity: 0.5;
      &.active {
        opacity: 1;
      }
    }
  }
  .slider-content {
    position: relative;
    left: 50%;
    top: 50%;
    width: 70%;
    height: 60%;
    transform: translate(-50%, -50%);
    .slider-single {
      position: absolute;
      z-index: 0;
      left: 0;
      top: 0;
      width: 100%;
      height: 100%;
      transition: z-index 0ms $delay;
      .slider-single-image {
        position: relative;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        box-shadow: 0px 10px 40px rgba(0, 0, 0, 0.2);
        transition: $time $mode;
        transform: scale(0);
        opacity: 0;
      }
      .slider-single-download {
        position: absolute;
        display: block;
        right: -22px;
        bottom: 12px;
        padding: 15px;
        color: #333333;
        background-color: #fdc84b;
        font-size: 18px;
        font-weight: 600;
        font-family: "karla";
        border-radius: 5px;
        box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.3);
        transition: $time $mode;
        opacity: 0;
        &:hover,
        &:focus {
          outline: none;
          text-decoration: none;
        }
      }
      .slider-single-title {
        display: block;
        float: left;
        margin: 16px 0 0 20px;
        font-size: 20px;
        font-family: "karla";
        font-weight: 400;
        color: #ffffff;
        transition: $time $mode;
        opacity: 0;
      }
      .slider-single-likes {
        display: block;
        float: right;
        margin: 16px 20px 0 0;
        transition: $time $mode;
        opacity: 0;
        i {
          font-size: 20px;
          display: inline-block;
          vertical-align: middle;
          margin-right: 5px;
          color: #ff6060;
          transition: $time $mode;
          transform: scale(0);
        }
        p {
          display: inline-block;
          vertical-align: middle;
          margin: 0;
          color: #ffffff;
        }
        &:hover,
        &:focus {
          outline: none;
          text-decoration: none;
        }
      }
      &.preactivede {
        //z-index: 0;
        .slider-single-image {
          //opacity: 0;
          transform: translateX(-50%) scale(0);
        }
      }
      &.preactive {
        z-index: 1;
        .slider-single-image {
          opacity: 0.3;
          transform: translateX(-25%) scale(0.8);
        }
        .slider-single-download {
          transform: translateX(-150px);
        }
        .slider-single-title {
          transform: translateX(-150px);
        }
        .slider-single-likes {
          transform: translateX(-150px);
        }
      }
      &.proactive {
        z-index: 1;
        .slider-single-image {
          opacity: 0.3;
          transform: translateX(25%) scale(0.8);
        }
        .slider-single-download {
          transform: translateX(150px);
        }
        .slider-single-title {
          transform: translateX(150px);
        }
        .slider-single-likes {
          transform: translateX(150px);
        }
      }
      &.proactivede {
        //z-index: 0;
        .slider-single-image {
          //opacity: 0;
          transform: translateX(50%) scale(0);
        }
      }
      &.active {
        z-index: 2;
        .slider-single-image {
          opacity: 1;
          transform: translateX(0%) scale(1);
        }
        .slider-single-download {
          opacity: 1;
          transition-delay: 100ms;
          transform: translateX(0px);
        }
        .slider-single-title {
          opacity: 1;
          transition-delay: 200ms;
          transform: translateX(0px);
        }
        .slider-single-likes {
          opacity: 1;
          transition-delay: 300ms;
          transform: translateX(0px);
          i {
            animation-name: heartbeat;
            animation-duration: 500ms;
            animation-delay: 900ms;
            animation-interation: 1;
            animation-fill-mode: forwards;
          }
        }
      }
    }
  }
  .slider-left {
    position: absolute;
    z-index: 3;
    display: block;
    right: 85%;
    top: 50%;
    color: #ffffff;
    transform: translateY(-50%);
    padding: 20px 15px;
    border-top: 2px solid #fdc84b;
    border-right: 2px solid #fdc84b;
    border-bottom: 2px solid #fdc84b;
    border-left: 2px solid #fdc84b;
    margin-right: -2px;
  }
  .slider-right {
    position: absolute;
    z-index: 3;
    display: block;
    left: 85%;
    top: 50%;
    color: #ffffff;
    transform: translateY(-50%);
    padding: 20px 15px;
    border-top: 2px solid #fdc84b;
    border-right: 2px solid #fdc84b;
    border-bottom: 2px solid #fdc84b;
    border-left: 2px solid #fdc84b;
    margin-left: -2px;
  }
  .not-visible {
    display: none !important;
  }
  
}
</style>