/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
const quantity = document.querySelectorAll(".quantity-input");
const decrease = document.querySelectorAll(".decrease");
const increase = document.querySelectorAll(".increase");
const formatter = Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND'
});

const removeButtons = document.querySelectorAll(".remove");
const totalCost = document.querySelector("#total-cost");
const cartSize = document.querySelector("#cart-size");
const cartBadge = document.querySelector("#cart-badge");

removeButtons.forEach(removeButtons => {
    removeButtons.addEventListener('click', _ => {
        const root = removeButtons.closest(".product");
        const foodID = root.querySelector("input[name=food-id]");
        $.ajax({
            type: 'POST',
            url: "MainServlet?action=customer-remove-food-cart",
            data: {
                foodId: foodID.value
            },
            success: respone => {
                const data = JSON.parse(respone);
                const result = data.result;
                if (result) {
                    totalCost.innerHTML = formatter.format(data.totalCost);
                    let content;
                    if (data.size > 1) {
                        content = `Total (${data.size} items):  `;
                    } else {
                        content = `Total (${data.size} item): `;
                        cartSize.innerHTML = content;
                        root.remove();
                    }
                    if (data.size <= 0) {
                        cartBadge.classList.remove("has-item");
                    } else if (data.size > 0 && data.size < 10) {
                        cartBadge.innerHTML = data.size;
                    } else {
                        cartBadge.innerHTML = '9+';
                    }

                }
            },
            error: e => console.log(e)
        });
    });
});

quantity.forEach(quantity => {
    quantity.addEventListener('click', _ => {
        const root = removeButtons.closest(".product");
        const total = root.querySelector(".product-total");
        const unitPrice = root.querySelector(".product-price");

        setTimeout(() => {
            $.ajax({
                type: "POST",
                url: "MainServlet?action=customer-update-cart",
                data: {
                    quantity: quantity.value,
                    foodId: root.querySelector("input[name=food-id]").value
                },
                success: response => {
                    const data = JSON.parse(response);
                    quantity.value = data.quantity;
                    total.innerHTML = formatter.format(parseFloat(unitPrice.innerHTML.replaceAll('.', '')) * data.quantity);
                    totalCost.innerHTML = formatter.format(parseFloat(data.totalCost));
                },
                error: e => console.log(e)
            });
        }, 100);

    })
})


decrease.forEach(decrease => {
    decrease.addEventListener('click', _ => {
        const root = decrease.closest(".product"); // 🔹 Lấy đúng sản phẩm
        const quantity = root.querySelector(".quantity-input");
        const total = root.querySelector(".product-total");
        const unitPrice = root.querySelector(".product-price");

        let num = parseInt(quantity.value, 10);
        if (num > 1) {
            quantity.value = --num;

            setTimeout(() => {
                $.ajax({
                    type: "POST",
                    url: "MainServlet?action=customer-update-cart",
                    data: {
                        quantity: quantity.value,
                        foodId: root.querySelector("input[name=food-id]").value
                    },
                    success: response => {
                        const data = typeof response === "string" ? JSON.parse(response) : response;

                        quantity.value = data.quantity;

                        let priceValue = parseFloat(unitPrice.innerHTML.replace(/[^\d.]/g, ""));
                        priceValue = Math.round(priceValue)

                        total.innerHTML = new Intl.NumberFormat('vi-VN', {
                            style: 'currency',
                            currency: 'VND',
                            maximumFractionDigits: 0
                        }).format(priceValue * data.quantity);

                        console.log("Unit price in de: ", priceValue);
                        console.log("Total price in de: ", total);

                        totalCost.innerHTML = new Intl.NumberFormat('vi-VN', {
                            style: 'currency',
                            currency: 'VND',
                            maximumFractionDigits: 0
                        }).format(parseFloat(data.totalCost));
                    },
                    error: e => console.log(e)
                });
            }, 100);
        }
    });
});




increase.forEach(increase => {
    increase.addEventListener('click', _ => {
        const root = increase.closest(".product");
        const quantity = root.querySelector(".quantity-input");
        const total = root.querySelector(".product-total");
        const unitPrice = root.querySelector(".product-price");

        console.log("Total: " + total);
        console.log("unit Price" + unitPrice)

        let num = parseInt(quantity.value, 10);
        quantity.value = ++num;

        setTimeout(() => {
            $.ajax({
                type: "POST",
                url: "MainServlet?action=customer-update-cart",
                data: {
                    quantity: quantity.value,
                    foodId: root.querySelector("input[name=food-id]").value
                },
                success: response => {
                    const data = typeof response === "string" ? JSON.parse(response) : response;

                    quantity.value = data.quantity;

                    let priceValue = parseFloat(unitPrice.innerHTML.replace(/[^\d.]/g, ""));
                    priceValue = Math.round(priceValue)

                    total.innerHTML = new Intl.NumberFormat('vi-VN', {
                        style: 'currency',
                        currency: 'VND',
                        maximumFractionDigits: 0
                    }).format(priceValue * data.quantity);

                    console.log("Unit price in inc: ", priceValue);
                    console.log("Total price in inc: ", total);

                    totalCost.innerHTML = new Intl.NumberFormat('vi-VN', {
                        style: 'currency',
                        currency: 'VND',
                        maximumFractionDigits: 0
                    }).format(parseFloat(data.totalCost));
                },
                error: e => console.log(e)
            });
        }, 100);
    });
});


//document.addEventListener("DOMContentLoaded", () => {
//    const processOrderBtn = document.getElementById("process-order-btn");
//    const cartSize = document.querySelectorAll(".product").length; // Đếm số sản phẩm trong giỏ hàng
//
//    processOrderBtn.addEventListener("click", () => {
//        if (cartSize === 0) {
//            alert("Giỏ hàng của bạn đang trống! Vui lòng thêm sản phẩm.");
//        } else {
//           window.location.href = "MainController?action=customer-place-order"; 
//        }
//    });
//});

//increase.forEach(increase => {
//    increase.addEventListener('click', _ => {
//        const root = increase.parentElement.parentElement.parentElement;
//        const quantity = increase.previousElementSibling;
//        const total = root.querySelector(".product-total");
//        const unitPrice = root.querySelector(".product-price");
//        console.log("total: " + total);
//        console.log("UnitPrice: " + unitPrice);
//
//        let num = +quantity.value;
//        quantity.value = ++num;
//
//        setTimeout(() => {
//            $.ajax({
//                type: "POST",
//                url: "MainServlet?action=customer-update-cart",
//                data: {
//                    quantity: quantity.value,
//                    foodId: root.querySelector("input[name=food-id]").value
//                },
//                
//                success: response => {
//                    const data = JSON.parse(response);
//                    quantity.value = data.quantity;
//                    total.innerHTML = formatter.format(parseFloat(unitPrice.innerHTML.replaceAll('.', '')) * data.quantity);
//                    totalCost.innerHTML = formatter.format(parseFloat(data.totalCost));
//                },
//                error: e => console.log(e)
//            });
//        }, 300);
//    });
//});


//decrease.forEach(decrease => {
//    decrease.addEventListener('click', _ => {
//        const root = decrease.parentElement.parentElement.parentElement;
//        const quantity = decrease.nextElementSibling;
//        const total = root.querySelector(".product-total");
//        const unitPrice = root.querySelector(".product-price");
//
//        let num = +quantity.value;
//        if (num > 1) {
//            quantity.value = --num;
//            setTimeout(() => {
//                $.ajax({
//                    type: "POST",
//                    url: "MainServlet?action=customer-update-cart",
//                    data: {
//                        quantity: quantity.value,
//                        foodId: root.querySelector("input[name=food-id]").value
//
//                    },
//                    success: response => {
//                        const data = JSON.parse(response);
//                        quantity.value = data.quantity;
//                        total.innerHTML = formatter.format(parseFloat(unitPrice.innerHTML.replaceAll('.', '')) * data.quantity);
//                        totalCost.innerHTML = formatter.format(parseFloat(data.totalCost));
//                    },
//                    error: e => console.log(e)
//                });
//            }, 300);
//        }
//    });
//});

