import React, { useState } from 'react'
import '../../css/pages/signupsignin.css'
import '../../css/reusables/positions.css'
import jsPDF from "jspdf";
import "jspdf-autotable";
    // REMINDER, YOU MUST IMPORT jsPDF, jspdf-autotable, and format from dateFNS*******


  const  report= carSales => {
    // initialize jsPDF
  const doc = new jsPDF();
    // define my columns
  const tableColumn = ["Id", "Make", "Model", "Year", "Sale Price"];
    // define an empty array of rows
  const tableRows = [];

  // for each car sale pass all its data into an array
  carSales.forEach(car => {
    const carData = [
      car.id,
      car.make,
      car.model,
      car.year,
      car.salePrice,

    ];
    // push each car sale info into a row
    tableRows.push(carData);
  });

    // startY is basically margin-top
    doc.autoTable(tableColumn, tableRows, { startY: 20 });
    const date = Date().split(" ");
    // we use a date string to generate our filename.
    const dateStr = date[0] + date[1] + date[2] + date[3] + date[4];
    // car info and margin-top + margin-left
    doc.text("Car Sales for the Requested Period", 14, 15);
    // we define the name of our PDF file.
    doc.save(`Sales_ report_${dateStr}.pdf`);
  };


export default report;