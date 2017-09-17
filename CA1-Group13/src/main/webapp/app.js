function handleErrors(response) {
    if (!response.ok) {
        var error = new Error(response.statusText);
        error = response;
        throw error;
    }
    return response.json();
}

function errorMessage(errorJson) {
    if (errorJson !== undefined) {
        message = errorJson.message;
        alert(message);
    }
}
function getPersonWithPhone() {
    var phoneId = document.getElementById("phoneId").value;
    if(phoneId ===""){
        alert("You need to type in a phonenumber");        
    } else {        
        var myHeaders = new Headers;
        myHeaders.set("Content-Type", "application/json");
        var promise = fetch("api/person/complete/" + phoneId,
                {
                    method: "GET",
                    headers: myHeaders
                });
        promise.then(handleErrors).then(function (person) {
            document.getElementById("tId").innerHTML = genSinglePersonTable(person);
        }).catch(function (error) {
            return error.json();
        }).then(errorMessage);
    }
}

function getAllPersons() {
    console.log("bob");
    var myHeaders = new Headers;
    myHeaders.set("Content-Type", "application/json");
    var promise = fetch("api/person/complete", {
        method: "GET",
        headers: myHeaders
    });
    promise.then(handleErrors).then(function (persons) {
        document.getElementById("tId").innerHTML = generatePersonsTable(persons);
    }).catch(function (error) {
        return error.json();
    }).then(errorMessage);
}

function getAllZipcodes() {
    console.log("preben");
    var myHeaders = new Headers;
    myHeaders.set("Content-Type", "application/json");
    var promise = fetch("api/zipcode", {
        method: "GET",
        headers: myHeaders
    });
    promise.then(function (response) {
        return response.json();
    }).then(function (zipcodes) {
        document.getElementById("tId").innerHTML = genZipcodesTable(zipcodes);
    });
}

function genZipcodesTable(zipcodes) {
    var htmlStr = "<thead><th>Zipcode</th><th>City</th></thead><tbody></tbody>";
    var newZipcodesArray = zipcodes.map(function (zipcode) {
        return "<tr><td>" + zipcode.zipcode
                + "</td><td>" + zipcode.city
                + "</td></tr>";
    });
    htmlStr += newZipcodesArray.join('');
    return htmlStr;
}

function generatePersonsTable(persons) {

    var htmlStr = "<thead><th>First name</th><th>Last name</th><th>Email</th><th>Address</th><th>Phones</th><th>Zipcode</th></thead><tbody>";
    var newPersonsArray = persons.map(function (person) {
        return "<tr><td>" + person.firstName
                + "</td><td>" + person.lastName
                + "</td><td>" + person.email
                + "</td><td>" + person.additionalInfo + "<br>" + person.street + "<br>" + person.city
                + "</td><td>" + phoneNumbersStr(person)
                + "</td><td>" + person.zipcode
                + "</td></tr>";
    });
    htmlStr += newPersonsArray.join('') + "</tbody>";
    return htmlStr;
}
function genSinglePersonTable(person) {
    var htmlStr = "<thead><th>First name</th><th>Last name</th><th>Email</th><th>Address</th><th>Phones</th><th>Zipcode</th></thead><tbody></tbody>";
    return htmlStr += ""
            + "<tr><td>" + person.firstName
            + "</td><td>" + person.lastName
            + "</td><td>" + person.email
            + "</td><td>" + person.additionalInfo + "<br>" + person.street + "<br>" + person.city
            + "</td><td>" + phoneNumbersStr(person)
            + "</td><td>" + person.zipcode
            + "</td></tr>";
}

function phoneNumbersStr(person) {
    var phonearray = person.phones;
    return phonearray.map(function (phone) {
        return phone.phoneNumber + "<br>" + phone.description;
    }).join('<br>');
}

function getAllPersonsByZipcode() {
    var zipcode = document.getElementById("phoneId").value;
    if (zipcode === "") {
        alert("You need to enter a zipcode");
    } else {
        var myHeaders = new Headers;
        myHeaders.set("Content-Type", "application/json");
        var promise = fetch("api/person/zipcode/" + zipcode, {
            method: "GET",
            headers: myHeaders
        });
        promise.then(handleErrors)
                .then(function (persons) {
                    document.getElementById("tId").innerHTML = generatePersonsTable(persons);
                }).catch(function (error) {
            return error.json();
        }).then(errorMessage);
    }
}
function getCompanyByCVR() {
    var CVR = document.getElementById("phoneId").value;
    if (CVR === "") {
        alert("You need to type an cvr!")
    } else {

        var myHeaders = new Headers;
        myHeaders.set("Content-Type", "application/json");
        var promise = fetch("api/company/complete/" + CVR, {
            method: "GET",
            headers: myHeaders
        });
        promise.then(handleErrors)
                .then(function (company) {
                    document.getElementById("tId").innerHTML = singleCompanyTable(company);
                })
                .catch(function (error) {
                    return error.json();
                }).then(errorMessage);
    }
}

function singleCompanyTable(company) {
    var htmlStr = "<thead><th>CVR</th><th>Company name</th><th>Email</th><th>Decription</th><th>Address</th><th>Phone</th><th>Market Value</th><th>Employee Number</th></thead><tbody>";
    return htmlStr += "" + "<tr><td>" + company.cvr
            + "</td><td>" + company.name
            + "</td><td>" + company.email
            + "</td><td>" + company.desc
            + "</td><td>" + company.streetInfo + "<br>" + company.street + "<br>" + company.city + "<br>" + company.zipCode
            + "</td><td>" + phoneNumbersStr(company)
            + "</td><td>" + company.mValue
            + "</td><td>" + company.numbEmp
            + "</td></tr></tbody>";
}

function genCompanyTable(companies) {

    var htmlStr = "<thead><th>CVR</th><th>Company name</th><th>Email</th><th>Decription</th><th>Address</th><th>Phone</th><th>Market Value</th><th>Employee Number</th></thead><tbody>";
    var newCompanyArray = companies.map(function (company) {
        return "<tr><td>" + company.cvr
                + "</td><td>" + company.name
                + "</td><td>" + company.email
                + "</td><td>" + company.desc
                + "</td><td>" + company.streetInfo + "<br>" + company.street + "<br>" + company.city + "<br>" + company.zipCode
                + "</td><td>" + phoneNumbersStr(company)
                + "</td><td>" + company.mValue
                + "</td><td>" + company.numbEmp
                + "</td>";
    });
    htmlStr += newCompanyArray.join("") + "</tbody>";
    return htmlStr;
}

function addNewPerson() {
    var personObject = {
        "firstName": document.getElementById("firstName").value,
        "lastName": document.getElementById("lastName").value,
        "email": document.getElementById("email").value,
        "phones": [
            {
                "phoneNumber": document.getElementById("phoneNumber").value,
                "description": document.getElementById("phoneDesc").value
            }
        ],
        "street": document.getElementById("street").value,
        "additionalInfo": document.getElementById("additionalInfo").value,
        "zipcode": document.getElementById("zipcode").value,
        "city": document.getElementById("city").value
    };


    var myHeaders = new Headers;
    myHeaders.set("Content-Type", "application/json");
    var promise = fetch("api/person/", {
        method: "POST",
        headers: myHeaders,
        body: JSON.stringify(personObject)
    });

    promise
            .then(handleErrors)
            .then(function (person) {
                document.getElementById("tId").innerHTML = genSinglePersonTable(person);
            })
            .catch(function (error) {
                return error.json();
            }).then(errorMessage);
}


function getAllCompanies() {
    var myHeaders = new Headers;
    myHeaders.set("Content-Type", "application/json");
    var promise = fetch("api/company/complete/", {
        method: "GET",
        headers: myHeaders
    });
    promise.then(handleErrors)
            .then(function (companies) {
                console.log(companies);
                document.getElementById("tId").innerHTML = genCompanyTable(companies);
                document.getElementById("")
            })
            .catch(function (error) {
                return error.json();
            }).then(errorMessage);


}
document.getElementById("addPersonBtn").addEventListener("click", addNewPerson);
document.getElementById("getAllCompaniesBtn").addEventListener("click", getAllCompanies);
document.getElementById("tId").innerHTML = getAllPersons();
document.getElementById("findByPhone").addEventListener("click", getPersonWithPhone);
document.getElementById("findAllPersonsbtn").addEventListener("click", getAllPersons);
document.getElementById("getAllZip").addEventListener("click", getAllZipcodes);
document.getElementById("getPersonsWithZipBtn").addEventListener("click", getAllPersonsByZipcode);
document.getElementById("getCompanyByCVRBtn").addEventListener("click", getCompanyByCVR);

