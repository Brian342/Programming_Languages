//.checked = property that determines the checked state of an HTML checkbox or radio button element
const myCheckBox = document.getElementById("myCheckBox")
const visaBtn = document.getElementById("visaBtn")
const masterCardBtn = document.getElementById("masterCardBtn")
const payPalBtn = document.getElementById("payPalBtn")
const mySubmit = document.getElementById("mySubmit")
const subResult = document.getElementById("subResult")
const paymentResult = document.getElementById("paymentResult")


mySubmit.onclick = function(){
    if(myCheckBox.checked){
        subResult.textContent = `You are Subscribed`
    }else{
        subResult.textContent = `You are Not Subscribed`
    }

    if(visaBtn.checked){
        paymentResult.textContent = `You are paying with visa`
    }else if(masterCardBtn.checked){
        paymentResult.textContent = `You are paying with Master Card`
    }else if(payPalBtn.checked){
        paymentResult.textContent = `you are paying with PayPal`
    }else{
        paymentResult.textContent = `You must select a payment type`
    }
}
