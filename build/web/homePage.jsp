<%-- 
    Document   : homePage
    Created on : Feb 18, 2025, 8:15:50 PM
    Author     : Asus
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Food Chill</title>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            />
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
            rel="stylesheet"
            />
        <link rel="stylesheet" href="./css/styleProduct.css" />


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <!-- Navbar -->
        <jsp:include page="navigation.jsp"></jsp:include>  
            <!--end nav-->

            <!-- Main Content -->
            <div class="container mt-4 flex-grow-1">
                <!-- Slider -->
                <div class="row mb-4">
                    <div class="col-12">
                        <div
                            id="carouselExampleIndicators"
                            class="carousel slide"
                            data-bs-ride="carousel"
                            >
                            <div class="carousel-indicators">
                                <button
                                    type="button"
                                    data-bs-target="#carouselExampleIndicators"
                                    data-bs-slide-to="0"
                                    class="active"
                                    ></button>
                                <button
                                    type="button"
                                    data-bs-target="#carouselExampleIndicators"
                                    data-bs-slide-to="1"
                                    ></button>
                                <button
                                    type="button"
                                    data-bs-target="#carouselExampleIndicators"
                                    data-bs-slide-to="2"
                                    ></button>
                            </div>
                            <div class="carousel-inner rounded">
                                <div class="carousel-item active">
                                    <img
                                        src="image/carousel-item1.jpg"
                                        class="d-block w-100"
                                        alt="discount 30%"
                                        style="height: 500px; object-fit: cover"
                                        />
                                </div>
                                <div class="carousel-item">
                                    <img
                                        src="image/carousel-item2.jpg"
                                        class="d-block w-100"
                                        alt="Give Away"
                                        style="height: 500px; object-fit: cover"
                                        />
                                </div>
                                <div class="carousel-item">
                                    <img
                                        src="image/carousel-item3.jpg"
                                        class="d-block w-100"
                                        alt="..."
                                        style="height: 500px; object-fit: cover"
                                        />
                                </div>
                            </div>
                            <button
                                class="carousel-control-prev"
                                type="button"
                                data-bs-target="#carouselExampleIndicators"
                                data-bs-slide="prev"
                                >
                                <span class="carousel-control-prev-icon"></span>
                            </button>
                            <button
                                class="carousel-control-next"
                                type="button"
                                data-bs-target="#carouselExampleIndicators"
                                data-bs-slide="next"
                                >
                                <span class="carousel-control-next-icon"></span>
                            </button>
                        </div>
                    </div>
                </div>

                <!-- Products Section -->
                <div class="row mb-5">
                    <div class="col-12">
                        <h2 class="text-center mb-4">Món ăn ưa thích</h2>
                    </div>
                </div>

                <!-- Products Grid -->


                <div class="row row-cols-1 row-cols-md-4 g-4 mb-5">
                    <!-- Product Card 1 -->
                <c:forEach items="${requestScope.listFood}" var="items">
                    <div class="col">
                        <div class="card h-100">
                            <img src="${items.img}" class="card-img-top" alt="Product" />
                            <div class="card-body">
                                <h5 class="card-title">${items.name}</h5>
                                <p class="product-price">${items.price}</p>
                                <p class="card-text">${items.desc}</p>
                            </div>

                            <!--Thêm js lấy sự kiện vào giỏ hàng-->
                            <c:if test="${not empty sessionScope.USER}">
                                <div class="card-footer bg-white border-top-0">

                                    <div class="d-flex justify-content-center mb-2">
                                        <input 
                                            class="form-control quantity-input text-center quantity-value" 
                                            style="width: 100px;"
                                            data-food-id="${food.id}" 
                                            name="quantity" 
                                            type="number" 
                                            value="1"
                                            placeholder="Số lượng">
                                    </div>

                                    <button class="btn btn-outline-primary w-100 add-to-cart" type="button"
                                            id="add-with-quantity" value="${food.id}">
                                        <i class="fas fa-shopping-cart me-2"></i>Thêm vào giỏ
                                    </button>

                                </div>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <!-- Introduction -->
            <div class="row mb-5">
                <div class="col-12">
                    <div class="card introduction-card">
                        <div class="card-body">
                            <h2 class="text-center mb-4">Giới thiệu về Chill Food</h2>
                            <p>
                                Chill Food là website chuyên cung cấp các món ăn ngon, đa dạng,
                                phù hợp với mọi lứa tuổi và sở thích. Với phương châm "Ngon –
                                Tiện – Nhanh", chúng tôi cam kết mang đến cho khách hàng những
                                bữa ăn chất lượng, đảm bảo vệ sinh an toàn thực phẩm. Thực đơn
                                của Chill Food bao gồm nhiều lựa chọn hấp dẫn như đồ ăn mặn, món
                                ăn chay, thủy hải sản, và đồ uống .
                            </p>
                            <p>
                                Chúng tôi đặc biệt chú trọng đến nguồn nguyên liệu tươi sạch,
                                được chọn lọc kỹ lưỡng từ những nhà cung cấp uy tín. Các món ăn
                                tại Chill Food không chỉ ngon miệng mà còn được chế biến theo
                                công thức đặc biệt, mang lại trải nghiệm ẩm thực mới lạ. Với
                                dịch vụ giao hàng nhanh chóng, tiện lợi, khách hàng có thể dễ
                                dàng đặt món chỉ với vài cú click chuột trên website.
                            </p>
                            <p>
                                Ngoài ra, Chill Food còn thường xuyên có chương trình khuyến
                                mãi, ưu đãi hấp dẫn dành cho khách hàng thân thiết. Nếu bạn đang
                                tìm kiếm một địa chỉ tin cậy để thưởng thức những món ăn ngon mà
                                không cần ra khỏi nhà, Chill Food chính là lựa chọn lý tưởng.
                                Hãy đặt hàng ngay để tận hưởng hương vị tuyệt vời từ Chill Food!
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--end main content-->

        <!-- Footer -->
        <jsp:include page="footer.jsp"></jsp:include>
        <!--end footer-->
    </body>
</html>
