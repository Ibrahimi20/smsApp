import React, { Component, useEffect, useState } from 'react';
import * as XLSX from 'xlsx';
import { Button, Container, Col, Row } from 'react-bootstrap';
import generatePDF from '../services/reportGenerator';
import axios from 'axios';
function MainScreen() {
  const [date, setdate] = useState();
  const [arraylist, setarraylist] = useState([]);
  var show = [];

  function addfile(event) {
    let file = event.target.files[0];
    let filereader = new FileReader();
    filereader.readAsArrayBuffer(file);
    filereader.onload = (e) => {
      let arrayBuffer = filereader.result;
      var data = new Uint8Array(arrayBuffer);
      var arr = [];
      for (var i = 0; i < data.length; i++) {
        arr[i] = String.fromCharCode(data[i]);
      }

      var bstr = arr.join('');
      var workbook = XLSX.read(bstr, { type: 'binary' });
      var first_sheet_name = workbook.SheetNames[0];
      var worksheet = workbook.Sheets[first_sheet_name];
      var res = XLSX.utils.sheet_to_json(worksheet);

      console.log(res);
      for (i = 0; i < res.length; i++) {
        show[i] = res[i];
      }
      setarraylist(show);
    };
  }

  useEffect(() => {
    console.log(arraylist);
  }, [date, arraylist]);

  return (
    <Container>
      <Col>
        <Row>
          <Col>
            <input
              type="file"
              placeholder="Telecharger le fichier"
              accept=".csv,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"
              onChange={(event) => {
                addfile(event);
              }}
            />
          </Col>

          <Col>
            <Button
              disabled={arraylist.length === 0 ? true : false}
              className="bgutton"
              onClick={async () => {
                await axios
                  .post('http://localhost:8080/savedata', arraylist, {
                    headers: {
                      'Content-Type': 'application/json',
                    },
                  })
                  .then(console.log('it is alright'))
                  .catch((e) => window.alert(e));
              }}
            >
              Enregistrer les donnees capturee
            </Button>
          </Col>
        </Row>

        <div className="separator">
          {arraylist.length === 0 ? (
            ''
          ) : (
            <div style={{ margin: '20px' }}>
              <p>
                le fichier que vous avez choisie contient {arraylist.length}{' '}
                Lignes.
              </p>
              <p>
                Si vous voulez sauvegarder ces donnees dans la base appuyer sur{' '}
                <b>le button a droit</b>
              </p>
              <ul>
                {arraylist.map((item) => (
                  <li key={item.nom}>
                    numobjetappel : {item.numobjetappel}/ contactinfo:{' '}
                    {item.contactinfo} / nom : {item.nom} / prenom :{' '}
                    {item.prenom}
                  </li>
                ))}
              </ul>
            </div>
          )}
        </div>

        <Row>
          <Col>
            <label>
              Entrer la date de rapport
              <input
                name="date"
                type="date"
                onChange={(eve) => {
                  setdate(eve.target.value);
                }}
              />
            </label>
          </Col>

          <Col>
            <Button
              disabled={date == null ? true : false}
              onClick={async () => {
                try {
                  const res = await axios.get('http://localhost:8080/getdate', {
                    params: { startDate: date },
                  });

                  console.log(res.data);
                  generatePDF(res.data);
                } catch (error) {
                  console.log(error);
                }
              }}
            >
              Generer le rapport du Jour Choisi
            </Button>
          </Col>
        </Row>
      </Col>
    </Container>
  );
}

export default MainScreen;
