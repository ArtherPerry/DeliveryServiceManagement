// Function to validate form
function validateForm() {
    var osname = document.getElementById('osname').value;

    if (osname === "") {
        alert("Complete all data");
        return false;
    }
}

// Function to update total field
function updateTotal() {
    var delifees = document.getElementById('delifees');
    var pamount = document.getElementById('pamount');
    var ospaid = document.getElementById('ospaid');
    var totalamount = document.getElementById('total');

    var delifeesValue = parseFloat(delifees.value) || 0;
    var pamountValue = parseFloat(pamount.value) || 0;
    var ospaidValue = parseFloat(ospaid.value) || 0;
    var total = (delifeesValue - ospaidValue) + pamountValue;
    totalamount.value = total;
}

// Attach event listeners to the input fields
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('delifees').addEventListener("input", updateTotal);
    document.getElementById('pamount').addEventListener("input", updateTotal);
    document.getElementById('ospaid').addEventListener("input", updateTotal);
});

// Example data for pickup chart
var pickupData = {
    labels: ["Ready To Deliver", "Waiting for Label Print"],
    datasets: [{
        data: [25, 75], // Example data values
        backgroundColor: ["#36A2EB", "#FF6384"]
    }]
};

// Example data for deliver chart
var deliverData = {
    labels: ["Total Ongoing", "Total Deliver", "Pending in office", "Total Cancel"],
    datasets: [{
        data: [30, 20, 10, 40], // Example data values
        backgroundColor: ["#FFCE56", "#FF6384", "#36A2EB", "#FF5733"]
    }]
};


// Create pickup chart
document.addEventListener('DOMContentLoaded', function() {
    var pickupChartCanvas = document.getElementById("pickupChart");
    var pickupChart = new Chart(pickupChartCanvas, {
        type: 'pie',
        data: pickupData,
        options: {
            tooltips: {
                callbacks: {
                    label: function(tooltipItem, data) {
                        var dataset = data.datasets[tooltipItem.datasetIndex];
                        var currentValue = dataset.data[tooltipItem.index];
                        var total = dataset.data.reduce(function(previousValue, currentValue) {
                            return previousValue + currentValue;
                        });
                        var percentage = Math.round((currentValue / total) * 100);
                        return data.labels[tooltipItem.index] + ': ' + currentValue + ' (' + percentage + '%)';
                    }
                }
            }
        }
    });
});

// Create deliver chart
document.addEventListener('DOMContentLoaded', function() {
    var deliverChartCanvas = document.getElementById("deliverChart");
    var deliverChart = new Chart(deliverChartCanvas, {
        type: 'pie',
        data: deliverData,
        options: {
            tooltips: {
                callbacks: {
                    label: function(tooltipItem, data) {
                        var dataset = data.datasets[tooltipItem.datasetIndex];
                        var currentValue = dataset.data[tooltipItem.index];
                        var total = dataset.data.reduce(function(previousValue, currentValue) {
                            return previousValue + currentValue;
                        });
                        var percentage = Math.round((currentValue / total) * 100);
                        return data.labels[tooltipItem.index] + ': ' + currentValue + ' (' + percentage + '%)';
                    }
                }
            }
        }
    });
});
// Example data for income chart
var incomeData = {
    labels: ["Income YGN", "Income MDY"],
    datasets: [{
        data: [70, 30], // Example data values
        backgroundColor: ["#FFCE56", "#FF6384"]
    }]
};

// Create income chart
document.addEventListener('DOMContentLoaded', function() {
    var incomeChartCanvas = document.getElementById("incomeChart");
    var incomeChart = new Chart(incomeChartCanvas, {
        type: 'pie',
        data: incomeData,
        options: {
            tooltips: {
                callbacks: {
                    label: function(tooltipItem, data) {
                        var dataset = data.datasets[tooltipItem.datasetIndex];
                        var currentValue = dataset.data[tooltipItem.index];
                        var total = dataset.data.reduce(function(previousValue, currentValue) {
                            return previousValue + currentValue;
                        });
                        var percentage = Math.round((currentValue / total) * 100);
                        return data.labels[tooltipItem.index] + ': ' + currentValue + ' (' + percentage + '%)';
                    }
                }
            }
        }
    });
});
