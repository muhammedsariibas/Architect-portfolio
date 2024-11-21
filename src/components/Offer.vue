<template>
  <div class="offer" data-aos="fade-up" data-aos-duration="600">
    <div class="header">
      <span class="line"></span>
      <h3>NE SUNUYORUZ</h3>
    </div>

    <div class="offer-list">
      <div class="item">
        <div class="icon">
          <i class="fa-solid fas fa-handshake"></i>
        </div>
        <div>
          <h4>MÜŞTERİ ODAKLI</h4>
          <p >
            Müşterilerimizin ihtiyaçlarını en iyi şekilde karşılamak için çalışıyoruz.
          </p>
        </div>
      </div>
      <div class="item">
        <div class="icon">
          <i class="fa-solid fas fa-lightbulb"></i>
        </div>
        <div>
          <h4>İNOVASYON</h4>
          <p >
            Sıra Dışı Tasarımlar, Benzersiz Mekânlar: İnovasyonla Geleceği İnşa Ediyoruz
          
          </p>
        </div>
      </div>
      <div class="item">
        <div class="icon"><i class="fa-solid fas fa-hammer"></i></div>
        <div>
          <h4>KALİTELİ İŞÇİLİK</h4>
          <p >
            İşçiliğimizle projeleri kusursuzca hayata geçiriyor, her detaya özen gösteriyoruz.  
          </p>
        </div>
      </div>
     
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.offer {
  width: 90%;
  margin: auto;
  margin-top: 10%;
  margin-bottom: 10%;
}
.offer span {
  width: 30%;
  background: #2c3e50;
  height: 2px;
  display: inline-block;
  /* margin-bottom: 7px; */
  margin-right: 20px;
}

.header {
  display: flex;
  width: 30%;
  margin: auto auto;
  align-content: center;
  align-items: center;
  margin-top: 10%;
  margin-bottom: 3%;
  color: #2C3E50;
}

.header h3 {
  font-size: 30px;
  text-transform: capitalize;
}
.offer-list {
  width: 100%;
  margin: auto;
  display: flex;
  justify-content: space-around;
}
.item {
  width: 20%;
  margin-top: 30px;
  display: flex;
}

.item h4 {
  font-size: 17px;
  font-weight: bold;
  padding-bottom: 10px;
}
.item p {
  font-size: 13px;
  font-weight: bold;
}

.icon {
  margin-right: 20px;
}
@media screen and (max-width: 420px) {
  .offer-list {
    display: block;
    overflow-x: hidden;
      color: #2C3E50;
  }

  .header {
    margin-right: -10px !important;
    margin-bottom: 80px;
    width: 90%;
    padding-top: 80px;
    
  }

  .offer span {
    width: 20%;
    background: #2c3e50;
    height: 2px;
    display: inline-block;
    margin-right: 10px;
  }

  .icon {
    font-size: 20px;
    margin-bottom: 10px;
  }

  .header h3 {
    font-size: 25px;
    text-transform: capitalize;
  }
  .item {
    width: 90%;
    display: block;
    margin: auto auto;
    text-align: center;
    margin-bottom: 50px;
  }
}
</style>