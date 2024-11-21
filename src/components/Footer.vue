<template>
  <div class="footer">
    <div class="socials">
      <div>
        <v-btn
          variant="text"
          href="https://www.linkedin.com/company/kddi%CC%87zaynmi%CC%87marlik/"
          icon="fa-brands fa-linkedin"
        ></v-btn>
      </div>
      <div>
        <v-btn
          variant="text"
          href="https://www.instagram.com/kddizaynmimarlik/"
          icon="fa-brands fa-instagram"
        ></v-btn>
      </div>
    </div>
    <div class="tel">info@kddizayn.com</div>
    <div class="tel">TEL: 535 698 84 57</div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.footer {
  width: 30%;
  margin: auto auto;
  padding-bottom: 40px;
  color: #2c3e50;
}

.socials {
  display: flex;
  justify-content: center;
}
.tel {
  padding-top: 30px;
  text-align: center;
  font-size: 20px;
  font-weight: 900 !important;
  font-style: italic;
}

@media screen and (max-width: 420px) {
  .tel {
    font-size: 13px;
  }
}
</style>