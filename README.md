# CRM Software

# CRM System API Documentation

This document provides detailed documentation for the CRM System API, including authentication, customer management, lead management, task management, and sales management.

## Base URL

The base URL for all API endpoints is `http://localhost:8080`.

---

## Authentication API

The Authentication API handles user registration and login.

### 1. User Login

- **Endpoint:** `/api/auth/signin`
- **Method:** `POST`
- **Description:** Authenticates a user and returns a JWT token upon successful login.
- **Authentication:** None required.
- **Request Body:**

```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

- **Example Response:**

```json
{
  "accessToken": "eyJhbGciOiJIUzUxMiJ9...",
  "tokenType": "Bearer",
  "id": 1,
  "email": "user@example.com",
  "role": "ROLE_SALES"
}
```

### 2. User Registration

- **Endpoint:** `/api/auth/signup`
- **Method:** `POST`
- **Description:** Registers a new user in the system.
- **Authentication:** None required.
- **Request Body:**

```json
{
  "fullName": "John Doe",
  "email": "john.doe@example.com",
  "password": "password123",
  "role": "ROLE_SALES"
}
```

---

## Customer Management API

The Customer Management API provides endpoints for managing customer data. All endpoints require a valid JWT token.

### 1. Get All Customers

- **Endpoint:** `/api/customers`
- **Method:** `GET`
- **Description:** Retrieves a list of all customers.
- **Authentication:** `Bearer Token`

### 2. Get Customer by ID

- **Endpoint:** `/api/customers/{id}`
- **Method:** `GET`
- **Description:** Retrieves a single customer by their ID.
- **Authentication:** `Bearer Token`

### 3. Create Customer

- **Endpoint:** `/api/customers`
- **Method:** `POST`
- **Description:** Creates a new customer.
- **Authentication:** `Bearer Token` (Requires `ADMIN` or `SALES` role)
- **Request Body:**

```json
{
  "name": "New Customer Inc.",
  "email": "contact@newcustomer.com",
  "phone": "123-456-7890",
  "company": "New Customer Inc.",
  "address": "123 Main St, Anytown, USA",
  "notes": "Initial contact made.",
  "salesRepId": 1
}
```

### 4. Update Customer

- **Endpoint:** `/api/customers/{id}`
- **Method:** `PUT`
- **Description:** Updates an existing customer.
- **Authentication:** `Bearer Token` (Requires `ADMIN` or `SALES` role)
- **Request Body:** (Same as create)

### 5. Delete Customer

- **Endpoint:** `/api/customers/{id}`
- **Method:** `DELETE`
- **Description:** Deletes a customer by their ID.
- **Authentication:** `Bearer Token` (Requires `ADMIN` role)

---

## Lead Management API

The Lead Management API handles lead creation and tracking. All endpoints require a valid JWT token.

### 1. Get All Leads

- **Endpoint:** `/api/leads`
- **Method:** `GET`
- **Description:** Retrieves all leads.
- **Authentication:** `Bearer Token`

### 2. Get Lead by ID

- **Endpoint:** `/api/leads/{id}`
- **Method:** `GET`
- **Description:** Retrieves a single lead by ID.
- **Authentication:** `Bearer Token`

### 3. Create Lead

- **Endpoint:** `/api/leads`
- **Method:** `POST`
- **Description:** Creates a new lead.
- **Authentication:** `Bearer Token` (Requires `ADMIN` or `SALES` role)
- **Request Body:**

```json
{
  "name": "Potential Client",
  "contactInfo": "client@example.com",
  "source": "Website",
  "status": "NEW",
  "salesRepId": 1
}
```

### 4. Update Lead

- **Endpoint:** `/api/leads/{id}`
- **Method:** `PUT`
- **Description:** Updates an existing lead.
- **Authentication:** `Bearer Token` (Requires `ADMIN` or `SALES` role)
- **Request Body:** (Same as create)

### 5. Delete Lead

- **Endpoint:** `/api/leads/{id}`
- **Method:** `DELETE`
- **Description:** Deletes a lead by ID.
- **Authentication:** `Bearer Token` (Requires `ADMIN` role)

---

## Task Management API

The Task Management API is used for managing tasks assigned to users. All endpoints require a valid JWT token.

### 1. Get All Tasks

- **Endpoint:** `/api/tasks`
- **Method:** `GET`
- **Description:** Retrieves all tasks.
- **Authentication:** `Bearer Token`

### 2. Get Task by ID

- **Endpoint:** `/api/tasks/{id}`
- **Method:** `GET`
- **Description:** Retrieves a single task by ID.
- **Authentication:** `Bearer Token`

### 3. Create Task

- **Endpoint:** `/api/tasks`
- **Method:** `POST`
- **Description:** Creates a new task.
- **Authentication:** `Bearer Token` (Requires `ADMIN` or `SALES` role)
- **Request Body:**

```json
{
  "title": "Follow up with Potential Client",
  "description": "Call to discuss proposal.",
  "dueDate": "2024-12-31",
  "priority": "HIGH",
  "assignedUserId": 1,
  "status": "PENDING"
}
```

### 4. Update Task

- **Endpoint:** `/api/tasks/{id}`
- **Method:** `PUT`
- **Description:** Updates an existing task.
- **Authentication:** `Bearer Token` (Requires `ADMIN` or `SALES` role)
- **Request Body:** (Same as create)

### 5. Delete Task

- **Endpoint:** `/api/tasks/{id}`
- **Method:** `DELETE`
- **Description:** Deletes a task by ID.
- **Authentication:** `Bearer Token` (Requires `ADMIN` role)

---

## Sales Management API

The Sales Management API tracks sales from proposal to closure. All endpoints require a valid JWT token.

### 1. Get All Sales

- **Endpoint:** `/api/sales`
- **Method:** `GET`
- **Description:** Retrieves all sales records.
- **Authentication:** `Bearer Token`

### 2. Get Sale by ID

- **Endpoint:** `/api/sales/{id}`
- **Method:** `GET`
- **Description:** Retrieves a single sale by ID.
- **Authentication:** `Bearer Token`

### 3. Create Sale

- **Endpoint:** `/api/sales`
- **Method:** `POST`
- **Description:** Creates a new sale record.
- **Authentication:** `Bearer Token` (Requires `ADMIN` or `SALES` role)
- **Request Body:**

```json
{
  "customerId": 1,
  "amount": 5000.00,
  "status": "PROPOSAL",
  "date": "2024-11-15",
  "salesRepId": 1
}
```

### 4. Update Sale

- **Endpoint:** `/api/sales/{id}`
- **Method:** `PUT`
- **Description:** Updates an existing sale.
- **Authentication:** `Bearer Token` (Requires `ADMIN` or `SALES` role)
- **Request Body:** (Same as create)

### 5. Delete Sale

- **Endpoint:** `/api/sales/{id}`
- **Method:** `DELETE`
- **Description:** Deletes a sale by ID.
- **Authentication:** `Bearer Token` (Requires `ADMIN` role)
