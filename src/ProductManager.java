import java.util.Scanner;

public class ProductManager {

    /*
1.	Xây dựng hàm thị danh sách các sản phẩm có trong mảng lên màn hình
        public static void printProductsArray(Product[] productArr)
2.	Xây dựng hàm thêm một sản phẩm vào mảng
        public static Product[] addToProductsArray(Product[] sourceArray, Product product)
3.	Xây dựng hàm xóa một sản phẩm khỏi mảng
        public static Product[] deleteFromProductsArray(Product[] sourceArray, Product product)
4.	Xây dựng hàm tìm kiếm một sản phẩm trong mảng theo tên của sản phẩm đó.
        public static Product searchProductInArrayByName(Product[] searchArray, String searchName)
5.	Xây dựng hàm sắp xếp các sản phẩm trong mảng theo thứ tự A->Z
        public static void sortProductArrayByName(Product[] products)
6.	Xây dựng hàm cập nhật sản phẩm

     */

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Product product1 = new Product("Dell", 1200, "Hello");
        Product product2 = new Product("Acer", 950);
        Product product3 = new Product("Asus", 1050);
        Product product4 = new Product("HP", 1100);
        Product product5 = new Product("Macbook", 1500, "This thing is expensive");
        Product[] productArr = {product1, product2, product3, product4, product5};

// 1.	Xây dựng hàm thị danh sách các sản phẩm có trong mảng lên màn hình
        System.out.println("Init products list: ");
        printProductsArray(productArr);

// 2.	Xây dựng hàm thêm một sản phẩm vào mảng
        Product product6 = new Product("Lenovo", 1200, "Added recently");
        product2.setDescription("This item will be delete soon");
        productArr = addToProductsArray(productArr, product6);
        System.out.println("Products list after adding one new product: ");
        printProductsArray(productArr);

// 3.	Xây dựng hàm xóa một sản phẩm khỏi mảng
        productArr = deleteFromProductsArray(productArr, product2);
        System.out.println("Products list after deleting one product: ");
        printProductsArray(productArr);


// 4.	Xây dựng hàm tìm kiếm một sản phẩm trong mảng theo tên của sản phẩm đó.
        System.out.println();
        System.out.print("Search for product name: ");

        String searchName = sc.next();
        Product searchResult = searchProductInArrayByName(productArr, searchName);
        if (searchResult != null) {
            System.out.println("Search result: ");
            printProductStringArray("ID", "Name", "Price ($)", "Description");
            printProduct(searchResult);
        } else {
            System.out.println("Product name not found.");
        }
        pressAnyKeyToContinue();

// 5.	Xây dựng hàm sắp xếp các sản phẩm trong mảng theo thứ tự A->Z
        System.out.println("Products list sorted (by name): ");
        sortProductArrayByName(productArr);
        printProductsArray(productArr);
        pressAnyKeyToContinue();

// 6.	Xây dựng hàm cập nhật sản phẩm
        editProductInfoPrompt(productArr);
        sortProductArrayByName(productArr);
        System.out.println("Products list editted and sorted: ");
        printProductsArray(productArr);
    }

    public static void editProductInfoPrompt(Product[] productArr) {
        System.out.print("Which product you want to edit? Enter ID: ");
        int searchID = sc.nextInt();
        for (Product product: productArr){
            if (product.getId() == searchID){
                printProductsArray(new Product[]{product}); // in ra thông tin cũ
                System.out.print("Enter new Product Name: ");
                String newName = sc.next();
                if (!newName.equals("")){   // nếu người dùng không nhập gì thì bỏ qua
                    product.setName(newName);
                }
                System.out.print("Enter new Price: ");
                Double newPrice = sc.nextDouble();  // dùng kiểu dữ liệu đóng gói (Double thay vì double)
                 if (!newPrice.isNaN()) product.setPrice(newPrice);

                System.out.print("Enter new Description: ");
                String newDes = sc.next();
                product.setDescription(newDes);
                return;
            }
        }
        System.out.println("Product ID not found.");
    }

    public static void sortProductArrayByName(Product[] products) {
        for (int i = 0; i < products.length - 1; i++) {
            for (int j = i + 1; j < products.length; j++) {
                if (products[i].getName().charAt(0) > products[j].getName().charAt(0)) {
                    Product temp = products[i];
                    products[i] = products[j];
                    products[j] = temp;
                }
            }
        }
    }

    public static void pressAnyKeyToContinue() {
        System.out.println("Press Enter key to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }

    public static Product searchProductInArrayByName(Product[] searchArray, String searchName) {
        for (Product product : searchArray) {
            if (product.getName().equals(searchName)) {
                return product;
            }
        }
        return null;
    }

    public static Product[] deleteFromProductsArray(Product[] sourceArray, Product product) {
        int index = -1;
        for (int i = 0; i < sourceArray.length; i++) {
            if (sourceArray[i].equals(product)) {
                index = i;
                break;
            }
        }

        Product[] result = new Product[sourceArray.length - 1];
        System.arraycopy(sourceArray, 0, result, 0, index);
        System.arraycopy(sourceArray, index + 1, result, index, sourceArray.length - index - 1);
        return result;
    }

    public static Product[] addToProductsArray(Product[] sourceArray, Product product) {
        Product[] result = new Product[sourceArray.length + 1];
        System.arraycopy(sourceArray, 0, result, 0, sourceArray.length);
        result[result.length - 1] = product;
        return result;
    }

    public static void printProductsArray(Product[] productArr) {
        printProductStringArray("ID", "Name", "Price ($)", "Description");
        for (int i = 0; i < productArr.length; i++) {
            printProduct(productArr[i]);
        }
    }

    public static void printProduct(Product product) {
        printProductStringArray(product.toStringArray());
    }

    public static void printProductStringArray(String... str) {
        System.out.printf("%-8s %-24s %-20s %s\n", str[0], str[1], str[2], str[3]);
    }

}
