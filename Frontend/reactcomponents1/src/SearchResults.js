import { useState } from "react";
import { Table, Button } from "semantic-ui-react";
import Edit from "./Edit";

function SearchResults(props) {
  const [displayEditPane, toggleDisplayEditPane] = useState(false);
  const [selectedData, setSelectedData] = useState(null);
  //handle empty results, this conditional probably needs to be modified to fit error message
  if (!props.searchResults) {
    return (
      <div>
        <h1>No Results</h1>
      </div>
    );
  } else if (displayEditPane) {
    return (
      <div>
        <Edit type={props.type} data={selectedData} />
      </div>
    );
  } else if (props.type === "customer") {
    return (
      <div>
        <Table celled padded>
          <Table.Header>
            <Table.Row>
              <Table.HeaderCell singleLine>Customer ID</Table.HeaderCell>
              <Table.HeaderCell>First Name</Table.HeaderCell>
              <Table.HeaderCell>Last Name</Table.HeaderCell>
              <Table.HeaderCell>Date of Birth</Table.HeaderCell>
              <Table.HeaderCell>License Number</Table.HeaderCell>
              <Table.HeaderCell>Loyalty Points</Table.HeaderCell>
              <Table.HeaderCell>{/*empty*/}</Table.HeaderCell>
            </Table.Row>
          </Table.Header>
          <Table.Body>
            {props.searchResults.map((result) => (
              <Table.Row key={result.customerId}>
                <Table.Cell>{result.customerId}</Table.Cell>
                <Table.Cell>{result.firstName}</Table.Cell>
                <Table.Cell>{result.lastName}</Table.Cell>
                <Table.Cell>{result.dateOfBirth}</Table.Cell>
                <Table.Cell>{result.licenseNumber}</Table.Cell>
                <Table.Cell>{result.loyaltyPoints}</Table.Cell>
                <Table.Cell>
                  <Button
                    onClick={() => {
                      setSelectedData(result);
                      toggleDisplayEditPane(true);
                    }}
                  ></Button>
                </Table.Cell>
              </Table.Row>
            ))}
          </Table.Body>
        </Table>
      </div>
    );
  }
  //placeholder, needs implementation
  else if (props.type === "inventory") {
    return (
      <div>
        <Table celled padded>
          <Table.Header>
            <Table.Row>
              <Table.HeaderCell singleLine>Customer ID</Table.HeaderCell>
              <Table.HeaderCell>First Name</Table.HeaderCell>
              <Table.HeaderCell>Last Name</Table.HeaderCell>
              <Table.HeaderCell>Date of Birth</Table.HeaderCell>
              <Table.HeaderCell>License Number</Table.HeaderCell>
              <Table.HeaderCell>Loyalty Points</Table.HeaderCell>
            </Table.Row>
          </Table.Header>
          <Table.Body>
            {props.searchResults.map((result) => (
              <Table.Row key={result.customerId}>
                <Table.Cell>{result.customerId}</Table.Cell>
                <Table.Cell>{result.firstName}</Table.Cell>
                <Table.Cell>{result.lastName}</Table.Cell>
                <Table.Cell>{result.dateOfBirth}</Table.Cell>
                <Table.Cell>{result.licenseNumber}</Table.Cell>
                <Table.Cell>{result.loyaltyPoints}</Table.Cell>
              </Table.Row>
            ))}
          </Table.Body>
        </Table>
      </div>
    );
  }
  //placeholder, needs implementation
  else if (props.type === "reservation") {
    return (
      <div>
        <Table celled padded>
          <Table.Header>
            <Table.Row>
              <Table.HeaderCell singleLine>Customer ID</Table.HeaderCell>
              <Table.HeaderCell>First Name</Table.HeaderCell>
              <Table.HeaderCell>Last Name</Table.HeaderCell>
              <Table.HeaderCell>Date of Birth</Table.HeaderCell>
              <Table.HeaderCell>License Number</Table.HeaderCell>
              <Table.HeaderCell>Loyalty Points</Table.HeaderCell>
            </Table.Row>
          </Table.Header>
          <Table.Body>
            {props.searchResults.map((result) => (
              <Table.Row key={result.customerId}>
                <Table.Cell>{result.customerId}</Table.Cell>
                <Table.Cell>{result.firstName}</Table.Cell>
                <Table.Cell>{result.lastName}</Table.Cell>
                <Table.Cell>{result.dateOfBirth}</Table.Cell>
                <Table.Cell>{result.licenseNumber}</Table.Cell>
                <Table.Cell>{result.loyaltyPoints}</Table.Cell>
              </Table.Row>
            ))}
          </Table.Body>
        </Table>
      </div>
    );
  }
}

export default SearchResults;
