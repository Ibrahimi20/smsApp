import jsPDF from 'jspdf';
import 'jspdf-autotable';

const generatePDF = (sms) => {
  const doc = new jsPDF();
  const tableColumn = [
    'NumObjetApp',
    'ContactInfo',
    'Nom',
    'Prenom',
    'Etat',
    'DateEnvoie',
  ];
  const tableRows = [];

  sms.forEach((e) => {
    const smsdata = [
      e.numObjetAppe,
      e.contactInfo,
      e.nom,
      e.prenom,
      e.etat,
      e.dateEnvoie,
    ];
    tableRows.push(smsdata);
  });

  doc.autoTable(tableColumn, tableRows, { startY: 20 });
  const date = Date().split(' ');
  const datestr = date[0] + date[1] + date[2] + date[3] + date[4];
  doc.text('SMS liste pour le jour J', 14, 15);
  doc.save(`rapoprt_${datestr}.pdf`);
};

export default generatePDF;
