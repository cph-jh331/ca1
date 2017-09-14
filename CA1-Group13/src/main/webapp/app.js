

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
        document.getElementById("personTableBody").innerHTML = genSinglePersonTable(person);
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
        document.getElementById("personTableBody").innerHTML = generatePersonsTable(persons);
    });
}

function genSinglePersonTable(person) {
    return ""
            + "<tr><td>" + person.firstName
            + "</td><td>" + person.lastName
            + "</td><td>" + person.email
            + "</td><td>" + person.additionalInfo + "<br>" + person.street + "<br>" + person.city
            + "</td><td>" + phoneNumbersStr(person)
            + "</td><td>" + person.zipcode
            + "</td></tr>";
}

function generatePersonsTable(persons) {

    var htmlStr = "";
    var newPersonsArray = persons.map(function (person) {
        return "<tr><td>" + person.firstName
                + "</td><td>" + person.lastName
                + "</td><td>" + person.email
                + "</td><td>" + person.additionalInfo + "<br>" + person.street + "<br>" + person.city
                + "</td><td>" + phoneNumbersStr(person)
                + "</td><td>" + person.zipcode
                + "</td></tr>";
    });
    htmlStr = newPersonsArray.join('');
    return htmlStr;

//            + "<tr><td>" + person.firstName
//            + "</td><td>" + person.lastName
//            + "</td><td>" + person.email
//            + "</td><td>" + person.additionalInfo + "<br>" + person.street + "<br>" + person.city
//            + "</td><td>" + phoneNumbersStr(person)
//            + "</td><td>" + person.zipcode
//            + "</td></tr>";

}

function phoneNumbersStr(person) {
    var phonearray = person.phones;
    return phonearray.map(function (phone) {
        return phone.phoneNumber + "<br>" + phone.description;
    }).join('<br>');
}

document.getElementById("findByPhone").addEventListener("click", getPersonWithPhone);
document.getElementById("findAllPersonsbtn").addEventListener("click", getAllPersons);