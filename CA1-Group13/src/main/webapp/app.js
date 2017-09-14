function getPersonWithPhone() {
    var phoneId = document.getElementById("phoneId").value

    var myHeaders = new Headers;
    myHeaders.set("Content-Type", "application/json")

    var response = fetch("CA1-Group13/api/person/complete/" + phoneId,
            {
                method: "GET",
                headers: myHeaders
            });
    response.then(function (response) {
        return response.json();
    }).then(function (person) {
        document.getElementById("personTableBody").innerHTML = generatePersonTable(person);
    });
}

/*
 <th>First name</th>
 <th>Last name</th>
 <th>Email</th>
 <th>Address</th>
 <th>Phones</th>
 <th>Zipcode</th>
 */
function generatePersonTable(person) {
    var htmlStr = ""
            + "<tr><td>" + person.firstName
            + "</td><td>" + person.lastName
            + "</td><td>" + person.email
            + "</td><td>" + person.additionInfo + "<br>" + person.street + "<br>" + person.city
            + "</td><td>" + phoneNumbersStr(person)
            + "</td><td>" + person.zipcode
            + "</td></tr>"
}

function phoneNumbersStr(person) {
    var phonearray = person.phones;
    return phonearray.map(function (phone) {
        return "<br>"
    }).join('');
}

document.getElementById("findByPhone").addEventListener("click", getPersonWithPhone);