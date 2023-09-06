# Person-Address relationship
There are several mapping possibilities for a Person and address relationship.<br> 
I've notably mentioned 3 versions for the same.<br>

`v1` **One-to-One Mapping** 
*Key highlights*
- Each person has only one address, and each address is associated with only one person.
- This is the most common mapping in real life.

I have to edit this readme...






`v2` **One-to-Many Mapping**
*Key highlights*
- Each person can have multiple addresses, but each address is associated with only one person.
  - This could occur if a person has multiple residencies or mailing addresses.

`v3` **Many-to-One Mapping**
*Key highlights*
- Each person has only one address, but multiple people share the same address.
  - This is common in situations like apartment buildings or dormitories.

`v4` **Many-to-Many Mapping**
*Key highlights*
- Each person can have multiple addresses, and multiple people can share the same address.
  - This could occur in situations like a shared household or a business with multiple employees operating out of same address.

<span style="color:gray">_It is important to note that there may be additional factors to consider when mapping
a person and address relationship, such as whether the address is permanent or temporary,
or whether multiple people live at the same address but have separate units or rooms._</span>
