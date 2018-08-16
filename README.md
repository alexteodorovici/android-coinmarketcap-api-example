# android-coinmarketcap-api-example
This application uses the new Professional API from pro.coinmarketcap.com to display the top 100 crypto coins and their data.

* features the API call to https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?limit=100
* integrated with Retrofit example
* Gson data model extracted using http://www.jsonschema2pojo.org/ 
* added X-CMC_PRO_API_KEY header to the GET API call
* example for Android RecyclerView list.
* example for list item click listener via interface that uses putExtra with the serialized coin Object for opening coin page activity and passing data.
* fully functional example
* well commented code

https://pro.coinmarketcap.com/api/v1#section/Quick-Start-Guide

Make sure to update the header with YOUR API KEY in the APIInterface class.

<p align="center">
  <img width="350"  src="device-2018-08-16-162437.png?raw=true">
   
  <img width="350"  src="device-2018-08-16-162530.png?raw=true">
</p>


