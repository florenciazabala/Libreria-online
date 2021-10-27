
        var oilCanvas = document.getElementById("myChart");

            var oilData = {
                labels: [
                    "Saudi Arabia",
                    "Russia",
                    "Iraq",
                    "United Arab Emirates",
                    "Canada"
                ],
                datasets: [
                    {
                        data: [133.3, 86.2, 52.2, 51.2, 50.2],
                        backgroundColor: [
                            "#FF6384",
                            "#63FF84",
                            "#84FF63",
                            "#8463FF",
                            "#6384FF"
                        ]
                    }]
            };

            var pieChart = new Chart(oilCanvas, {
            type: 'pie',
            data: oilData
            });
