`1. Delete Index`

DELETE http://localhost:9200/product_index/

`2. Search Index`

GET http://localhost:9200/product_index/_search

`3. Find All Products`

GET http://localhost:8080/v1/api/product/findAll

`4. Create or Update a Product Document`

curl --location 'http://localhost:8080/v1/api/product/createOrUpdateDocument' \
--header 'Content-Type: application/json' \
--data '{
    "id": 2,
    "productName": "Cake",
    "price": 15
}'


`5. Get Specific Product by ID`
curl --location 'http://localhost:8080/v1/api/product/getDocument?productId=1'

`6. Search All Products`
curl --location 'http://localhost:8080/v1/api/product/searchDocument'

`7. Delete Specific Product`
curl --location --request DELETE 'http://localhost:8080/v1/api/product/deleteDocument?productId=1'



