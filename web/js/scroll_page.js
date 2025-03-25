/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Phân trang
let offset = 0;
const limit = 6;
let loading = false;

function loadMore() {
    if (loading)
        return;
    loading = true;
    $('#loading').show();

    fetch(`MainServlet?action=scroll&offset=${offset}&limit=${limit}`)
            .then(response => {
                if (!response.ok)
                    throw new Error("Lỗi kết nối server");
                return response.json();
            })
            .then(data => {
                if (!data || data.length === 0) {
                    $('#loading').text("Không còn món ăn nào.");
                    window.removeEventListener('scroll', onScroll);
                    return;
                }

                const container = document.getElementById("food-list");
                data.forEach(food => {
                    const html = `
                    <div class="col">
                        <div class="card h-100">
                            <img src="${food.img}" class="card-img-top" alt="${food.name}">
                            <div class="card-body">
                                <h5 class="card-title">
                                    <a href="MainServlet?action=category&foodId=${food.id}">${food.name}</a>
                                </h5>
                                <p class="text-danger">${food.price} VND</p>
                                <p class="card-text">${food.desc}</p>
                            </div>
                            <div class="card-footer bg-white border-top-0">
                                <div class="d-flex justify-content-center mb-2">
                                    <input class="form-control text-center" 
                                           style="width: 100px;" 
                                           data-food-id="${food.id}" 
                                           type="number" value="1" />
                                </div>
                                <button class="btn btn-outline-primary w-100 add-to-cart" 
                                        type="button" value="${food.id}">
                                    <i class="fas fa-shopping-cart me-2"></i>Thêm vào giỏ
                                </button>
                            </div>
                        </div>
                    </div>`;
                    container.insertAdjacentHTML('beforeend', html);
                });

                offset += limit;
                loading = false;
                $('#loading').hide();
            })
            .catch(err => {
                console.error("Lỗi khi load dữ liệu:", err);
                $('#loading').text("Có lỗi xảy ra khi tải dữ liệu.");
            });
}

function onScroll() {
    if (window.innerHeight + window.scrollY >= document.body.offsetHeight - 100) {
        loadMore();
    }
}

window.addEventListener('scroll', onScroll);
window.addEventListener('DOMContentLoaded', loadMore);



document.addEventListener("DOMContentLoaded", function () {
    const container = document.getElementById("food-list");
    if (!container) return;

    container.addEventListener("click", function (e) {
        const target = e.target.closest(".add-to-cart");
        if (!target) return;

        const foodId = target.value;
        const quantityInput = document.querySelector(`input[data-food-id="${foodId}"]`);
        const quantity = quantityInput ? quantityInput.value : 1;

        $.ajax({
            url: "MainServlet?action=add-to-cart",
            method: "GET",
            data: {
                foodId: foodId,
                quantity: quantity
            },
            success: function (response) {
                const data = typeof response === "string" ? JSON.parse(response) : response;
                if (data.result) {
                    const badge = document.querySelector("#cart-badge");
                    if (badge) {
                        badge.classList.add("has-item");
                        badge.innerHTML = data.size > 9 ? "9+" : data.size;
                    }
                    alert("Đã thêm vào giỏ hàng!");
                } else {
                    alert(data.message || "Bạn cần đăng nhập trước!");
                }
            },
            error: function () {
                alert("Có lỗi xảy ra khi thêm vào giỏ hàng.");
            }
        });
    });
});