/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


const citySelect = document.querySelector("#city");
const districtSelect = document.querySelector("#district");
const wardSelect = document.querySelector("#ward");

function resetList(list, content) {
    list.innerHTML = `<option disabled selected value=''>${content}</option>`;
}

// ✅ Lấy danh sách tỉnh/thành phố với `value` là `name`
function fetchCities() {
    fetch('https://provinces.open-api.vn/api/p/')
        .then(response => response.json())
        .then(cities => {
            resetList(citySelect, 'Select City / Province');
            cities.forEach(city => {
                citySelect.innerHTML += `<option value="${city.name}">${city.name}</option>`;
            });
        })
        .catch(err => console.error(err));
}

// ✅ Lấy danh sách quận/huyện với `value` là `name`
function fetchDistricts(provinceName) {
    fetch(`https://provinces.open-api.vn/api/p/`)
        .then(response => response.json())
        .then(provinces => {
            let selectedProvince = provinces.find(province => province.name === provinceName);
            if (selectedProvince) {
                fetch(`https://provinces.open-api.vn/api/p/${selectedProvince.code}?depth=2`)
                    .then(response => response.json())
                    .then(province => {
                        resetList(districtSelect, 'Select District');
                        province.districts.forEach(district => {
                            districtSelect.innerHTML += `<option value="${district.name}">${district.name}</option>`;
                        });
                        districtSelect.disabled = false;
                    })
                    .catch(err => console.error(err));
            }
        })
        .catch(err => console.error(err));
}

// ✅ Lấy danh sách phường/xã với `value` là `name`
function fetchWards(districtName) {
    fetch(`https://provinces.open-api.vn/api/d/`)
        .then(response => response.json())
        .then(districts => {
            let selectedDistrict = districts.find(district => district.name === districtName);
            if (selectedDistrict) {
                fetch(`https://provinces.open-api.vn/api/d/${selectedDistrict.code}?depth=2`)
                    .then(response => response.json())
                    .then(district => {
                        resetList(wardSelect, 'Select Ward');
                        district.wards.forEach(ward => {
                            wardSelect.innerHTML += `<option value="${ward.name}">${ward.name}</option>`;
                        });
                        wardSelect.disabled = false;
                    })
                    .catch(err => console.error(err));
            }
        })
        .catch(err => console.error(err));
}

// ✅ Event listener để chọn quận/huyện khi chọn thành phố
citySelect.addEventListener('change', () => {
    const provinceName = citySelect.value;
    resetList(districtSelect, 'Loading...');
    resetList(wardSelect, 'Select Ward');
    districtSelect.disabled = true;
    wardSelect.disabled = true;
    fetchDistricts(provinceName);
});

// ✅ Event listener để chọn phường/xã khi chọn quận/huyện
districtSelect.addEventListener('change', () => {
    const districtName = districtSelect.value;
    resetList(wardSelect, 'Loading...');
    wardSelect.disabled = true;
    fetchWards(districtName);
});

// ✅ Khởi động lấy danh sách tỉnh/thành phố ngay khi trang tải xong
fetchCities();
