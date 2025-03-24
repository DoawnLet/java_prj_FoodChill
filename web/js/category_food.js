document.querySelectorAll(".add-to-cart").forEach(addButton => {
    addButton.addEventListener("click", () => {
        const foodId = addButton.value;
        const quantityInput = document.querySelector(`input[data-food-id='${foodId}']`);

        if (!quantityInput) {
            console.error("Không tìm thấy input số lượng!");
            return;
        }

        const quantity = quantityInput.value;

        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/Food-Chill/MainServlet?action=add-to-cart',
            data: {foodId, quantity},
            success: function (response) {
                console.log("Full server response (before parsing):", response);

                try {
                    let jsonData = typeof response === "string" ? JSON.parse(response) : response;
                    console.log("Parsed JSON response:", jsonData);

                    if (jsonData.result) {
                        const cartBadge = document.querySelector("#cart-badge");
                        if (cartBadge) {
                            cartBadge.classList.add("has-item");
                            cartBadge.innerHTML = jsonData.size > 9 ? "9+" : jsonData.size;
                        }
                        alert("Added successfully!");
                    } else {
                        alert("You need to login first to use this feature!");
                    }
                } catch (error) {
                    console.error("Lỗi khi phân tích JSON:", error, response);
                }
            }
//            success: ({ result, size }) => {
//                console.log("Result: ", result);
//                console.log("Size: ", size);
//                if (result) {
//                    const cartBadge = document.querySelector("#cart-badge");
//                    if (cartBadge) {
//                        cartBadge.classList.add("has-item");
//                        cartBadge.innerHTML = size > 9 ? "9+" : size;
//                    }
//                    alert("Added successfully!");
//                } else {
//                    alert("You need to login first to use this feature!");
//            }
//            },
//            error: e => console.log("Lỗi khi gửi request:", e)
        });
    });
});


document.querySelectorAll(".add-to-cart").forEach(addButton => {
    addButton.addEventListener("click", () => {
        const foodId = addButton.value;
        console.log("Food ID:", foodId); // Kiểm tra giá trị foodId

        const quantityInput = document.querySelector(`input[data-food-id='${foodId}']`);
        console.log("Quantity Input:", quantityInput); // Kiểm tra có tìm thấy input không

        if (!quantityInput) {
            console.error("Không tìm thấy input số lượng!");
            return;
        }

        const quantity = quantityInput.value;
        console.log("Quantity:", quantity);
    });
});