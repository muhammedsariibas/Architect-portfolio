<template>
  <div class="general" data-aos="zoom-in" data-aos-duration="1000">
    <h2>İletişim</h2>

    <div class="contact">
      <div class="text">
        <h3>Sorunuz mu var?</h3>
        <p>Bize sorun, biz her zaman</p>
        <p>cevaplamaktan mutluluk duyarız</p>
      </div>
      <div class="inner-form">
        <iframe
        src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3010.0082149122422!2d28.812431576221336!3d41.02507621838999!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x14caa5b3eb863a73%3A0xd315a7ae7499525c!2sKD%20Dizayn%20ve%20Mimarl%C4%B1k!5e0!3m2!1str!2str!4v1692175725697!5m2!1str!2str"
        width="100%"
        height="450"
        style="border: 0"
        allowfullscreen=""
        loading="lazy"
        referrerpolicy="no-referrer-when-downgrade"
      ></iframe>
      G Plus, Bağlar mahallesi Mimarsinan Caddesi NO:37/1 İç Kapı:211, A Blok, 34209 Bağcılar/İstanbul
      </div>
    </div>
  </div>
</template>

<script>
export default {
  mounted() {
    // AOS.init();
  },
};
</script>

<style scoped>
.general {
  width: 100%;
  margin: auto auto;
  padding-bottom: 200px;
}

h2 {
  text-align: center;
  margin-bottom: 80px;
  font-size: 70px;
  font-weight: 600;
  letter-spacing: 10px;
   color: #2C3E50;
}
.contact {
  width: 100%;
}

input,
textarea {
  border: none;
  border-bottom: 1.5px solid rgb(179, 168, 168);
  background: transparent;
  width: 90%;
  margin-bottom: 40px;
  outline: none;
  padding: 10px;
}

input {
  width: 260px;
  padding-top: 9px;
}

label {
  display: block;

  font-size: 13px;
}
.first {
  display: flex;
  width: 90%;
  justify-content: space-between;
}
.contact {
  width: 100%;
  position: relative;
}

.contact {
  display: flex;
  justify-content: space-between;
  align-content: center;
  align-items: center;
    color: #2C3E50;
}

form {
  width: 80%;
  margin: auto 30%;
}
.text h3 {
  font-size: 55px;
  margin-bottom: 10px;
  font-weight: 600;
}
.text {
  top: -20px;
  left: 10%;
  z-index: 1;
}
.text p {
  font-size: 13px;
  padding-bottom: 10px;
}
.inner-form {
  width: 70%;
  position: relative;
  background-color: rgba(249, 249, 249, 0.4);
  padding: 100px;
}

@media screen and (max-width: 420px) {
  .contact {
 display: block;
   overflow-x: hidden;
}
.inner-form {
  width: 100%;
}
form {
  width: 100%;
  margin: auto auto;
}
.first {
  display: block;
  width: 100%;
}
.text h3 {
  font-size: 30px;
 padding-top: 20px;
}

h2 {
  text-align: center;
  margin-bottom: 40px;
  font-size: 50px;
  font-weight: 600;
  letter-spacing: 5px;

}


}
</style>