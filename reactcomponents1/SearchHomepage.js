import { useState } from "react";
import { Button, Segment, Grid, Header } from "semantic-ui-react";
import SearchFields from "./SearchFields";
function SearchHomepage() {
  //conditional display, use buttons to set state
  const [window, setWindow] = useState(null);
  return (
    <div>
      <Segment.Group>
        <Header as="h1" block textAlign="center">
          Search and Edit
        </Header>
        <Grid columns={3}>
          <Grid.Column textAlign="center">
            <Button floated="center" onClick={() => setWindow("customer")}>
              Customer
            </Button>
          </Grid.Column>
          <Grid.Column textAlign="center">
            <Button onClick={() => setWindow("inventory")}>Inventory</Button>
          </Grid.Column>
          <Grid.Column textAlign="center">
            <Button onClick={() => setWindow("reservation")}>
              Reservation
            </Button>
          </Grid.Column>
        </Grid>
      </Segment.Group>
      <SearchFields field={window} />
    </div>
  );
}

export default SearchHomepage;
