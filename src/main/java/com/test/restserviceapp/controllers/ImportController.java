package com.test.restserviceapp.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.test.restserviceapp.components.Category;
import com.test.restserviceapp.components.Product;
import com.test.restserviceapp.services.CategoryService;
import com.test.restserviceapp.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@RestController
@RequestMapping("/")
public class ImportController {

    private CategoryService categoryService;
    private ProductService productService;

    @Autowired
    public ImportController(ProductService productServiceArg, CategoryService categoryServiceArg)
    {
        this.productService = productServiceArg;
        this.categoryService = categoryServiceArg;
        return;
    }

    @GetMapping("/import")
    @ResponseBody
    public List<Product> importFromUrl(@RequestParam(required = true) String url)
    {
        /*
            Logstash didn't want to work properly
            So I decided to write own parser
            Here I'm just parsing xml from url
            And saving to ElasticSearch
        */
        try{

            ArrayList<Product> importedList = new ArrayList<Product>();
            ArrayList<Category> importedCategoriesList = new ArrayList<Category>();

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new URL(url).openStream());

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("category");

            for(int idx = 0; idx < nList.getLength(); idx++)
            {
                Node node = nList.item(idx);

                if(node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element)node;

                    Integer id = Integer.parseInt(element.getAttribute("id"));
                    String categoryName = node.getTextContent();
                    Integer parentCategoryId = Integer.parseInt(element.getAttribute("parentId"));

                    importedCategoriesList.add(new Category(id, categoryName, parentCategoryId));
                }
            }

            categoryService.createCategoryIndexBulk((List<Category>)importedCategoriesList);

            nList = doc.getElementsByTagName("product");

            for(int idx = 0; idx < nList.getLength(); idx++)
            {
                Node node = nList.item(idx);

                if(node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element)node;

                    Integer productIdArg = Integer.parseInt(element.getAttribute("id"));
                    Integer categoryIdArg = Integer.parseInt(((Element)element.getElementsByTagName("category_id").item(0)).getTextContent());
                    String productNameArg = ((Element)element.getElementsByTagName("category_id").item(0)).getTextContent();
                    String productDescriptionArg = ((Element)element.getElementsByTagName("category_id").item(0)).getTextContent();
                    Integer productPriceArg = Integer.parseInt(((Element)element.getElementsByTagName("category_id").item(0)).getTextContent());
                    String productImageUrlArg = ((Element)element.getElementsByTagName("category_id").item(0)).getTextContent();

                    importedList.add(new Product(productIdArg, categoryIdArg,
                                                productNameArg, productDescriptionArg,
                                                productPriceArg, productImageUrlArg));
                }
            }

            productService.createProductIndexBulk(importedList);

            return (List<Product>)importedList;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
}
