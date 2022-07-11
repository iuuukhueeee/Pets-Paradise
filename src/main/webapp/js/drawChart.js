let TOTAL_INCOME_A_MONTH = {
    loadData: function () {
        let chart = {}

        $.ajax({
            async: false,
            url: "AdminChartController",
            dataType: "json",
            success: function (chartData) {
                chart = chartData
                // $.each(chartData, function (index, data) {
                //     console.log(data)
                // })
            }
        })
        return chart
    },

    createChart: function (chartData) {
        // var label = chartData.total_data.$map(function(e) {
        //     return e.month
        // })
        var label = $.map(chartData, function(val, i) {
            return val[i][month]
        })

        // let data = chartData.total_data.$map(function(e) {
        //     return e.total
        // })
        var data = $.map(chartData, function (val, i) {
            return val[i][total]
        })

        return {
            type: 'bar',
            data: {
                // labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
                // labels: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12],
                lables: label,
                datasets: [{
                    label: 'My First Dataset',
                    data: data,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(255, 159, 64, 0.2)',
                        'rgba(255, 205, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(153, 102, 255, 0.2)',
                        'rgba(201, 203, 207, 0.2)'
                    ],
                    borderColor: [
                        'rgb(255, 99, 132)',
                        'rgb(255, 159, 64)',
                        'rgb(255, 205, 86)',
                        'rgb(75, 192, 192)',
                        'rgb(54, 162, 235)',
                        'rgb(153, 102, 255)',
                        'rgb(201, 203, 207)'
                    ],
                    borderWidth: 1
                }]
            }
        }
    },

    renderChart: function () {
        let context = document.getElementById("canvas").getContext("2d")

        let chart = new Chart(context, this.createChart())
    },

    initTotalIncomeAMonth: function () {
        let data = TOTAL_INCOME_A_MONTH.loadData();
        TOTAL_INCOME_A_MONTH.createChart(data);
        TOTAL_INCOME_A_MONTH.renderChart();
    }
};

$(document).ready(function () {
    TOTAL_INCOME_A_MONTH.initTotalIncomeAMonth()
})