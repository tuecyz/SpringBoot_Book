//document.addEventListener('DOMContentLoaded', function() {
function getirKitapTurleri() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var kitapTuruJson = JSON.parse(this.responseText);

            var selectElement = document.getElementById("turu_id");

            selectElement.innerHTML = "";

            kitapTuruJson.forEach(function(kitapTuru) {
                var optionElement = document.createElement("option");
                optionElement.value = kitapTuru.id;
                optionElement.textContent = kitapTuru.aciklama;
                selectElement.appendChild(optionElement);
            });
        }
    };
    xhttp.open("GET", "/kitap-turu/kitap-turu-getir", true);
    xhttp.send();
}

function ajaxcall(url) {

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            if (this.responseText == null || this.responseText == ''){
                alert("Kullanıcı adınız veya parolanız hatalı!!!");
            } else {
                document.location = 'main.html';
            }

        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}

function giris() {
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    ajaxcall("http://localhost:8080/user/login?username=" + username + "&password=" + password);
}

function anaMenuOlustur(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let KitapTuruJson = JSON.parse(this.responseText);
            let menuHtml = "";
            for (const kitapTuru of KitapTuruJson) {
                menuHtml += '<li><a href="#" onclick="getKitapByTuru('+kitapTuru.id+')">'+kitapTuru.aciklama+'</a></li>';
            }
            document.getElementById('main-menu').innerHTML = menuHtml;
        }
    };
    xhttp.open("GET", "/kitap-turu/kitap-turu-getir", true);
    xhttp.send();
}


function getKitapByTuru(kitapTuruId) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let kitaplarJson = JSON.parse(this.responseText);
            renderHtml(kitaplarJson);
        }
    };
    xhttp.open("GET", "/api/kitaplari-getir-kitap-turu?kitapTuru="+kitapTuruId, true);
    xhttp.send();
}

function renderHtml(json_data){

    let table_body = document.getElementById('kitapTable');
    table_body.innerHTML = '';

    json_data.forEach(function(item) {
        var row = document.createElement('tr');
        row.innerHTML = '<td>' + item.id + '</td>' +
            '<td>' + item.adi + '</td>' +
            '<td>' + item.yazari + '</td>' +
            '<td>' + item.turu + '</td>' +
            '<td><button onclick="kitapDuzelt(' + item.id + ')">Düzenle</button></td>'+
            '<td><button onclick="kitapSil(' + item.id + ')">Sil</button></td>' ;
            table_body.appendChild(row);
    });
}

function kitapEkle() {
    const adi = document.getElementById("adi").value;
    const yazari = document.getElementById("yazari").value;
    const turu_id = document.getElementById("turu_id").value;
    const aciklama = document.getElementById("aciklama").value;

    if (!adi || !yazari || !turu_id || !aciklama) {
        alert("Lütfen tüm bilgileri giriniz.");
        return false;
    }
    console.log(adi,yazari,turu_id,aciklama);
    const kitapView = {
        adi: adi,
        yazari: yazari,
        turu_id: turu_id,
        aciklama: aciklama
    };

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            if (this.responseText == null || this.responseText == ''){
                alert("Kitap ekleme işleminde bir hata oluştu. Lütfen girişlerinizi kontrol edin.");
            } else {
                alert("Kitap başarıyla eklendi!");
            }

        }
    };
    xhttp.open("POST", "/api/kitap-kaydet", true);
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(JSON.stringify(kitapView));
}
function kitapDuzelt(id) {
    if (!id) {
        alert("Lütfen düzeltmek istediğiniz kitabın ID'sini giriniz.");
        return false;
    }

    var yeniAdi = prompt("Yeni kitap adını giriniz:");
    var yeniYazari = prompt("Yeni yazar adını giriniz:");
    var yeniTuru = prompt("Yeni kitap türünü giriniz:");

    if (!yeniAdi || !yeniYazari || !yeniTuru) {
        alert("Lütfen tüm alanları doldurun.");
        return false;
    }

    var kitapBilgisi = {
        adi: yeniAdi,
        yazari: yeniYazari,
        turu: yeniTuru
    };

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4) {
            if (this.status == 200) {
                alert("Kitap başarıyla düzenlendi!");
            } else if (this.status == 404) {
                alert("Düzenlemeye çalıştığınız kitap bulunamadı.");
            } else {
                alert("Kitap düzenleme işleminde bir hata oluştu. Lütfen tekrar deneyin.");
            }
        }
    };

    xhttp.open("PUT", "/api/kitap-bilgisi-duzelt?id=" + id, true);
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(JSON.stringify(kitapBilgisi));
}

function kitapSil(id) {

    if (!id) {
        alert("Lütfen silmek istediğiniz kitabın ID'sini giriniz.");
        return false;
    }

    const confirmation = confirm("Bu kitabı gerçekten silmek istiyor musunuz?");

    if (!confirmation) {
        return false;
    }
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            if (this.responseText == null || this.responseText == ''){
                alert("Kitap silme işleminde bir hata oluştu. Lütfen tekrar deneyin.");
            } else {
                alert("Kitap başarıyla silindi!");
            }

        }
        else if (this.status == 404) {
            alert("Silmeye çalıştığınız kitap bulunamadı.");
        }
    };
    xhttp.open("DELETE", "/api/kitap-sil?id=" + id,  true);
    xhttp.send();
}
function anaMenuOlustur2() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            KitapTuruJson = JSON.parse(this.responseText);

            let selectHtml = "";
            for (const kitapTuru of KitapTuruJson) {
                selectHtml += '<option value="' + kitapTuru.id+ '">' + kitapTuru.aciklama + '</option>';
            }
            document.getElementById('kitap-turleri').innerHTML = selectHtml;
        }
    };
    xhttp.open("GET", "/kitap-turu/kitap-turu-getir", true);
    xhttp.send();
}

function getKitapTurleri() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var kitapTuruJson = JSON.parse(this.responseText);

            var tableBody = document.getElementById("kitapTable");
            tableBody.innerHTML = "";

            for (const kitapTuru of kitapTuruJson) {
                var tableRow = document.createElement("tr");

                var idCell = document.createElement("td");
                idCell.textContent = kitapTuru.id;
                tableRow.appendChild(idCell);

                var aciklamaCell = document.createElement("td");
                aciklamaCell.textContent = kitapTuru.aciklama;
                tableRow.appendChild(aciklamaCell);

                var duzenleButtonCell = document.createElement("td");
                var duzenleButton = document.createElement("button");
                duzenleButton.textContent = "Düzenle";

                duzenleButton.onclick = function() {
                    yenikitapTuruDuzelt(kitapTuru.id);
                };
                duzenleButtonCell.appendChild(duzenleButton);
                tableRow.appendChild(duzenleButtonCell);

                var silButtonCell = document.createElement("td");
                var silButton = document.createElement("button");
                silButton.textContent = "Sil";

                silButton.onclick = function() {
                    yenikitapTuruSil(kitapTuru.id);
                };
                silButtonCell.appendChild(silButton);
                tableRow.appendChild(silButtonCell);

                tableBody.appendChild(tableRow);
            }
        }
    };
    xhttp.open("GET", "/kitap-turu/kitap-turu-getir", true);
    xhttp.send();
}

getKitapTurleri();

function yenikitapTuruEkle() {
    var aciklama = document.getElementById("aciklama").value;
    var kitapTuruView = {
        aciklama: aciklama
    };

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            if (this.responseText == null || this.responseText == ''){
                alert("Kitap türü ekleme işleminde bir hata oluştu. Lütfen girişlerinizi kontrol edin.");
            } else {
                alert("Kitap türü başarıyla eklendi!");
            }

        }
    };
    xhttp.open("POST", "/kitap-turu/kitap-turu-kaydet", true);
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(JSON.stringify(kitapTuruView));
}

function yenikitapTuruDuzelt(id) {
    if (!id) {
        alert("Lütfen düzeltmek istediğiniz kitabın ID'sini giriniz.");
        return false;
    }

    var yeniTurAdi = prompt("Yeni kitap türü adını giriniz:");

    if (!yeniTurAdi) {
        alert("Lütfen tüm alanları doldurun.");
        return false;
    }

    var kitapTuruView = {
        id: id,
        aciklama: yeniTurAdi
    };

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            if (this.responseText == null || this.responseText == '') {
                alert("Kitap türü güncelleme işleminde bir hata oluştu. Lütfen girişlerinizi kontrol edin.");
            } else {
                alert("Kitap türü başarıyla düzeltildi!");
            }
        }
    };
    xhttp.open("PUT", "/kitap-turu/kitap-turu-duzelt?id=" + id, true);
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(JSON.stringify(kitapTuruView));
}

function yenikitapTuruSil(id) {

    if (!id) {
        alert("Lütfen silmek istediğiniz kitap türünün ID'sini giriniz.");
        return false;
    }

    const confirmation = confirm("Bu kitap türünü gerçekten silmek istiyor musunuz?");

    if (!confirmation) {
        return false;
    }
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            if (this.responseText == null || this.responseText == ''){
                alert("Kitap türü silme işleminde bir hata oluştu. Lütfen tekrar deneyin.");
            } else {
                alert("Kitap türü başarıyla silindi!");
            }

        }
        else if (this.status == 404) {
            alert("Silmeye çalıştığınız kitap türü bulunamadı.");
        }
    };
    xhttp.open("DELETE", "/kitap-turu/kitap-turu-sil?id=" + id, true);
    xhttp.send();
}

