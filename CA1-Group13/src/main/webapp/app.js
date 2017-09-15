

function getPersonWithPhone() {
    var phoneId = document.getElementById("phoneId").value;

    var myHeaders = new Headers;
    myHeaders.set("Content-Type", "application/json");

    var promise = fetch("api/person/complete/" + phoneId,
            {
                method: "GET",
                headers: myHeaders
            });
    promise.then(function (response) {
        return response.json();
    }).then(function (person) {
        document.getElementById("tId").innerHTML = genSinglePersonTable(person);
    });
}

function getAllPersons() {
    console.log("bob");
    var myHeaders = new Headers;
    myHeaders.set("Content-Type", "application/json");
    var promise = fetch("api/person/complete", {
        method: "GET",
        headers: myHeaders
    });
    promise.then(function (response) {
        return response.json();
    }).then(function (persons) {
        document.getElementById("tId").innerHTML = generatePersonsTable(persons);
    });
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

    var htmlStr = "<thead><th>First name</th><th>Last name</th><th>Email</th><th>Address</th><th>Phones</th><th>Zipcode</th></thead><tbody></tbody>";
    var newPersonsArray = persons.map(function (person) {
        return "<tr><td>" + person.firstName
                + "</td><td>" + person.lastName
                + "</td><td>" + person.email
                + "</td><td>" + person.additionalInfo + "<br>" + person.street + "<br>" + person.city
                + "</td><td>" + phoneNumbersStr(person)
                + "</td><td>" + person.zipcode
                + "</td></tr>";
    });
    htmlStr += newPersonsArray.join('');
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
            + "</td></tr>"
}

function phoneNumbersStr(person) {
    var phonearray = person.phones;
    return phonearray.map(function (phone) {
        return phone.phoneNumber + "<br>" + phone.description;
    }).join('<br>');
}

function getAllPersonsByCity() {
    var zipcode = document.getElementById("phoneId").value;
    var promise = fetch("api/zipcode/" + zipcode);
    promise.then(function (response) {
        return response.json();


    }).then(function (persons) {
        document.getElementById("tId").innerHTML = generatePersonsTable(persons);


    })


}
function getCompanyByCVR() {
    var CVR = document.getElementById("phoneId").value;
    var promise = fetch("api/company/complete/" + CVR);
    promise.then(function (response) {
        return response.json();

    }).then(function (company) {

        document.getElementById("tId").innerHTML = CompanyTabel(company);
    })

}
function CompanyTabel(company) {
    var htmlStr = "<thead><th>CVR</th><th>Company name</th><th>Email</th><th>Decription</th><th>Address</th><th>Phone</th><th>Market Value</th><th>Employee Number</th></thead><tbody></tbody>";

    return htmlStr += ""+"<tr><td>" + company.cvr
            + "</td><td>" + company.name
            + "</td><td>" + company.email
            + "</td><td>" + company.desc
            + "</td><td>" + company.streetInfo + "<br>" + company.street + "<br>" + company.city + company.zipCode
            + "</td><td>" + phoneNumbersStr(company)
            + "</td><td>" + company.mValue
            + "</td><td>" + company.numbEmp
            + "</td></tr>";
    };
   

//function CompanyTabel(company){
//      var htmlStr = "<thead><th>CVR</th><th>Company name</th><th>Email</th><th>Decription</th><th>Address</th><th>Phone</th><th>Market Value</th><th>Employee Number</th></thead><tbody></tbody>";
//       var newCompanyArray = company(function (company) {
//        return "<tr><td>" + company.cvr
//                + "</td><td>" + company.name
//                + "</td><td>" + company.email
//        + "</td><td>" + company.desc
//                + "</td><td>" + company.streetInfo + "<br>" + company.street + "<br>" + company.city+company.zipCode
//                + "</td><td>" + phoneNumbersStr(company)
//        + "</td><td>" + company.mValue
//+ "</td><td>" + company.numbEmp
//                + "</td></tr>";
//    });
//    htmlStr += newCompanyArray.join('');
//    return htmlStr;
//}

document.getElementById("findByPhone").addEventListener("click", getPersonWithPhone);
document.getElementById("findAllPersonsbtn").addEventListener("click", getAllPersons);
document.getElementById("getAllZip").addEventListener("click", getAllZipcodes);
document.getElementById("getPersonsByZipcode").addEventListener("click", getAllPersonsByCity);
document.getElementById("getCompanyByCVR").addEventListener("click", getCompanyByCVR);

