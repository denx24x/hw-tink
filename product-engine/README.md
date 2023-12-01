Диаграмма бд: "database diagram.png"

Имеет две ручки:

### POST /disbursement

Пример данных:

```
{
    "agreement_id": 2,
    "disbursement_date": "2012-04-23T18:25:43.511Z"
}
```

### POST /createAgreement

Пример данных:

```
{
    "client_id": 10,
    "product_code": "CL",
    "product_version": "1.0",
    "loan_term": 5,
    "disbursement_amount": 53000,
    "interest": 10,
    "origination_amount": 3000
}
```