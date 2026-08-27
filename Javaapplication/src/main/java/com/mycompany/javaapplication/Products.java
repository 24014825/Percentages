/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaapplication;

/**
 *
 * @author LENOVO
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Products {
    private ArrayList<ReportData> productList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void DisplayMenu() {
        while (true) {
            System.out.println("\nBRIGHT FUTURE TECHNOLOGIES APPLICATION");
            System.out.println("Enter (1) to launch menu or any other key to exit");
            String choice = scanner.nextLine();

            if (!choice.equals("1")) {
                ExitApplication();
                break;
            }

            System.out.println("\nPlease select one of the following menu items:");
            System.out.println("(1) Capture a new product.");
            System.out.println("(2) Search for a product.");
            System.out.println("(3) Update a product.");
            System.out.println("(4) Delete a product.");
            System.out.println("(5) Print report.");
            System.out.println("(6) Exit Application.");
            
            System.out.print("Enter choice: ");
            String menuOption = scanner.nextLine();

            switch (menuOption) {
                case "1":
                    CaptureProduct();
                    break;
                case "2":
                    SearchProduct();
                    break;
                case "3":
                    UpdateProduct();
                    break;
                case "4":
                    DeleteProduct();
                    break;
                case "5":
                    PrintReport();
                    break;
                case "6":
                    ExitApplication();
                    return;
                default:
                    System.out.println("Invalid selection. Returning to main menu.");
                    break;
            }
        }
    }

    public void CaptureProduct() {
        System.out.println("\nCAPTURE A NEW PRODUCT");
        System.out.print("Enter the product code: ");
        String code = scanner.nextLine();

        System.out.print("Enter the product name: ");
        String name = scanner.nextLine();

        // Product Category Selection
        String category = "";
        while (true) {
            System.out.println("Select the product category:");
            System.out.println("Desktop Computer => 1");
            System.out.println("Laptop           => 2");
            System.out.println("Tablet           => 3");
            System.out.println("Printer          => 4");
            System.out.println("Gaming Console   => 5");
            System.out.print("Product Category => ");
            String catOption = scanner.nextLine();

            if (catOption.equals("1")) {
                category = "Desktop Computer";
                break;
            } else if (catOption.equals("2")) {
                category = "Laptop";
                break;
            } else if (catOption.equals("3")) {
                category = "Tablet";
                break;
            } else if (catOption.equals("4")) {
                category = "Printer";
                break;
            } else if (catOption.equals("5")) {
                category = "Gaming Console";
                break;
            } else {
                System.out.println("Invalid category selected! Please enter a valid product category.\n");
            }
        }

        // Warranty Selection
        System.out.print("Indicate the product warranty. Enter (1) for 6 months or any other key for 2 years: ");
        String warrantyChoice = scanner.nextLine();
        String warranty = warrantyChoice.equals("1") ? "6 months" : "2 years";

        // Price Input
        double price = 0.0;
        while (true) {
            try {
                System.out.print("Enter the price for " + name + ": ");
                price = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid monetary amount.");
            }
        }

        // Stock Level Input
        int stockLevel = 0;
        while (true) {
            try {
                System.out.print("Enter the stock level for " + name + ": ");
                stockLevel = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer stock quantity.");
            }
        }

        System.out.print("Enter the supplier for " + name + ": ");
        String supplier = scanner.nextLine();

        ReportData product = new ReportData(code, name, category, warranty, price, stockLevel, supplier);
        SaveProduct(product);
        System.out.println("Product details have been saved successfully!!!");
    }

    public void SaveProduct(ReportData product) {
        productList.add(product);
    }

    public void SearchProduct() {
        System.out.print("\nPlease enter the product code to search: ");
        String code = scanner.nextLine();
        ReportData foundProduct = findByCode(code);

        if (foundProduct != null) {
            System.out.println("\nPRODUCT SEARCH RESULTS");
            System.out.println("--------------------------------------------------");
            System.out.println("PRODUCT CODE:\t\t" + foundProduct.getProductCode());
            System.out.println("PRODUCT NAME:\t\t" + foundProduct.getProductName());
            System.out.println("PRODUCT CATEGORY:\t" + foundProduct.getProductCategory());
            System.out.println("PRODUCT WARRANTY:\t" + foundProduct.getProductWarranty());
            System.out.println("PRODUCT PRICE:\t\t" + "R " + foundProduct.getProductPrice());
            System.out.println("PRODUCT STOCK LEVEL:\t" + foundProduct.getProductStockLevel());
            System.out.println("PRODUCT SUPPLIER:\t" + foundProduct.getProductSupplier());
            System.out.println("--------------------------------------------------");
        } else {
            System.out.println("The product cannot be located. Invalid Product");
        }
    }

    public void UpdateProduct() {
        System.out.print("\nPlease enter the product code to update: ");
        String code = scanner.nextLine();
        ReportData product = findByCode(code);

        if (product == null) {
            System.out.println("The product cannot be located. Invalid Product");
            return;
        }

        // Update Warranty
        System.out.print("Update the warranty? (y) Yes, (n) No: ");
        String updateWarranty = scanner.nextLine();
        if (updateWarranty.equalsIgnoreCase("y")) {
            System.out.print("Indicate the product warranty. Enter (1) for 6 months or any other key for 2 years: ");
            String warrantyChoice = scanner.nextLine();
            product.setProductWarranty(warrantyChoice.equals("1") ? "6 months" : "2 years");
        }

        // Update Price
        System.out.print("Update the product price? (y) Yes, (n) No: ");
        String updatePrice = scanner.nextLine();
        if (updatePrice.equalsIgnoreCase("y")) {
            while (true) {
                try {
                    System.out.print("Enter the new price for " + product.getProductName() + " >> ");
                    double newPrice = Double.parseDouble(scanner.nextLine());
                    product.setProductPrice(newPrice);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid numeric value.");
                }
            }
        }

        // Update Stock Level
        System.out.print("Update the stock level? (y) Yes, (n) No: ");
        String updateStock = scanner.nextLine();
        if (updateStock.equalsIgnoreCase("y")) {
            while (true) {
                try {
                    System.out.print("Enter the new stock level for " + product.getProductName() + " >> ");
                    int newStock = Integer.parseInt(scanner.nextLine());
                    product.setProductStockLevel(newStock);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                }
            }
        }

        System.out.println("Product details details have been updated successfully!!!");
    }

    public void DeleteProduct() {
        System.out.print("\nPlease enter the product code to delete: ");
        String code = scanner.nextLine();
        ReportData product = findByCode(code);

        if (product == null) {
            System.out.println("The product cannot be located. Invalid Product");
            return;
        }

        System.out.print("Are you sure you want to delete product " + code + "? (y) Yes, (n) No: ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("y")) {
            productList.remove(product);
            System.out.println("Product has been successfully deleted.");
        } else {
            System.out.println("Product deletion cancelled.");
        }
    }

    public void PrintReport() {
        if (productList.isEmpty()) {
            System.out.println("\nNo products found in the system to display.");
            return;
        }

        System.out.println("\n==================================================");
        System.out.println("PRODUCT REPORT");
        System.out.println("==================================================");

        double totalValue = 0.0;
        int count = 1;

        for (ReportData p : productList) {
            System.out.println("PRODUCT " + count);
            System.out.println("--------------------------------------------------");
            System.out.println("PRODUCT CODE >>\t\t" + p.getProductCode());
            System.out.println("PRODUCT NAME >>\t\t" + p.getProductName());
            System.out.println("PRODUCT CATEGORY >>\t" + p.getProductCategory());
            System.out.println("PRODUCT WARRANTY >>\t" + p.getProductWarranty());
            System.out.println("PRODUCT PRICE >>\t" + p.getProductPrice());
            System.out.println("PRODUCT LEVEL >>\t" + p.getProductStockLevel());
            System.out.println("PRODUCT SUPPLIER >>\t" + p.getProductSupplier());
            System.out.println("--------------------------------------------------");
            
            totalValue += p.getProductPrice();
            count++;
        }

        int totalCount = productList.size();
        double averageValue = totalCount > 0 ? (totalValue / totalCount) : 0.0;

        System.out.println("==================================================");
        System.out.println("TOTAL PRODUCT COUNT: " + totalCount);
        System.out.println("TOTAL PRODUCT VALUE: R " + String.format("%.1f", totalValue));
        System.out.println("AVERAGE PRODUCT VALUE: R " + Math.round(averageValue));
        System.out.println("==================================================");
    }

    public void ExitApplication() {
        System.out.println("\nExiting application... Goodbye!");
        System.exit(0);
    }

    // Helper method to look up a product by code
    private ReportData findByCode(String code) {
        for (ReportData p : productList) {
            if (p.getProductCode().equalsIgnoreCase(code)) {
                return p;
            }
        }
        return null;
    }
}
