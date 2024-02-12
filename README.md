
# Contact List

## Make with SpringBoot 3, MySQL & Angular 17

#### Made By GAMG-94

This project is a kind of digital agenda in which you can add, edit, update and delete contacts.

This project is intended to be scalable, although it is simple, I am developing it in such a way that it is a complete project, with its respective exception handling, validations and security implementations.


# API Reference

## Get all contacts

```http
  GET /api/contact/list
```


## Get contact by id

```http
  GET /api/contact/list${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Long` | **Required**. Id of contact to fetch |

## New contact

#### All fields are validated and should not be null and blank

```http
  POST /api/contact/list/new
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Long` | Is not required, it is automatically created and auto-incremented. |
| `name`      | `String` | **Required**. The name of the contact |
| `numberPhone` | `String` | **Required**. Has a minimum of 1 character and a maximum of 9 characters. |
| `email`      | `String` | **Required**. It can be anyone, but it must have the @ and the .com |
| `dateRegister`      | `Date` | Not required as the record is automatically created in the database. |


## Update contact

#### At the time of update, bring the selected contact information to improve the UX to make changes more effectively.

```http
  PUT /api/contact/list/update
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `name`      | `String` | **Required**. The name of the contact |
| `numberPhone` | `String` | **Required**. Has a minimum of 1 character and a maximum of 9 characters. |
| `email`      | `String` | **Required**. It can be anyone, but it must have the @ and the .com |

## Delete contact

#### Only needed the id of the contact

```http
  DELETE /api/contact/list/delete/{id}
```
