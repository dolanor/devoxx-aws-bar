// DOM Ready =============================================================
$(document).ready(function () {
    // Add User button click
    $('#btnAddCommand').on('click', addCommand);
    $('#btnRegisterToUrl').on('click', registerToURL);
});


function addCommand(event) {
    event.preventDefault();
    let beer    = $('input#inputBeerCommand').val();
    let beeramt = $('input#inputBeerQty').val();
    let food    = $('input#inputFoodCommand').val();
    let foodamt = $('input#inputFoodQty').val();

    let data = {food: {item:food ,amount:Number(foodamt)},beer: {item:beer, amount:Number(beeramt)}};
    console.log("stringified: ",JSON.stringify(data));
    $.ajax({
        type: 'POST',
        data: JSON.stringify(data),
        url: '/addCommand',
        contentType: "application/json; charset=utf-8",
        dataType: 'JSON'
    }).done(function (response) {
        // Check for successful (blank) response
        console.log(`Front::addCommand`, response)
        if (response.msg === '') {
            console.log("OK")
        }
    });
}

function registerToURL(event) {
    event.preventDefault();
    let urlSend = $('#registrationUrl').val();
    console.log(`registering url <${urlSend}>`);
    let data = {urlSend : urlSend }
    $.ajax({
        type: 'POST',
        data: JSON.stringify(data),
        url: '/registerApi',
        contentType: "application/json; charset=utf-8",
        dataType: 'JSON'
    }).done(function (response) {
        // Check for successful (blank) response
        console.log(`Front::registerToURL: ${response}`)
        if (response.msg === '') {
            console.log("registered OK")
        }
    });
}
