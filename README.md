# Base64-encryption
Scala Base 64 encryption and decryption

# Guide and functions

return encrypted string base on the parameter

Base64.encrypt("Man")

// result
  "gD4M"
  
return decrypting some encrypted string base on 64

Base64.decrypt("gD4M")

// result
  "Man"
  
  
for checking the encryption and the other 

Base64.code("Man", "gD4M")

// result
  true
