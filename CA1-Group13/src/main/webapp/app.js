

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

function phoneNumbersStr(person) {
    var phonearray = person.phones;
    return phonearray.map(function (phone) {
        return phone.phoneNumber + "<br>" + phone.description;
    }).join('<br>');
}

document.getElementById("findByPhone").addEventListener("click", getPersonWithPhone);
document.getElementById("findAllPersonsbtn").addEventListener("click", getAllPersons);
document.getElementById("getAllZip").addEventListener("click", getAllZipcodes);