# urlshortnerdemo
A Url shortening appliction written using SpringBoot.

Usage Instruction
1. Download the executable jar from executable folder.
2. Go to directory where you save the appliction, and open terminal.
3. give this command in termina java -jar urlshortner-0.0.1-SNAPSHOT.jar
4. Open Postman or Use Curl. Do a post with on url localhost:9000 and body { "url":"www.phoronix.com" }.
5. In response you will get short url in message.
6. Do a get with localhost:9000/{shorturl}, you will be redirected to original page.

Database
1. H2 database is used in file mode. A file will be create in your home directory.
2. To access database console, you can log into localhost:9000/h2

