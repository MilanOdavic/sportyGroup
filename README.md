# F1 Betting System

A Spring Boot application for betting on Formula 1 events using the OpenF1 API.

## Prerequisites

- Java 17 or higher
- Maven
- PostgreSQL/MySQL database (or H2 for testing)

## Setup

1. **Clone the repository**
   ```bash
   cd sportyGroup
   ```

2. **Configure database**
   
   Update `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/sportygroup
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Build and run**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access the application**
   - API: http://localhost:8080

## API Endpoints

### 1. User Management

#### Create User
```bash
POST /person/addPerson
Content-Type: application/json

{
  "name": "John Doe",
  "age": 30,
  "balance": 1000.0
}
```

#### Get All Users
```bash
GET /person/getAllPersons
```

#### Get User by ID
```bash
GET /person/getPersonById?id={userId}
```

### 2. F1 Sessions

#### Get F1 Sessions with Driver Market
```bash
GET /f1/sessions?sessionType=Sprint&year=2023&country=Belgium
```

**Query Parameters:**
- `sessionType` (optional): Sprint, Race, Qualifying
- `year` (optional): 2023, 2024, etc.
- `country` (optional): Belgium, Monaco, etc.

**Response:**
```json
[
  {
    "session": {
      "sessionKey": 9158,
      "sessionName": "Sprint",
      "sessionType": "Race",
      "countryName": "Belgium",
      "year": 2023,
      "dateStart": "2023-07-29T12:00:00",
      "dateEnd": "2023-07-29T13:00:00"
    },
    "driverMarket": [
      {
        "fullName": "Max Verstappen",
        "driverNumber": 1,
        "odds": 3
      }
    ]
  }
]
```

### 3. Place Bet

```bash
POST /bets/place
Content-Type: application/json

{
  "userId": "uuid-here",
  "sessionKey": 9158,
  "driverNumber": 1,
  "amount": 50.0
}
```

**Response:**
```json
{
  "betId": "bet-uuid",
  "newBalance": 950.0,
  "message": "Bet placed successfully"
}
```

### 4. Process Event Outcome

```bash
POST /events/outcome
Content-Type: application/json

{
  "sessionKey": 9158,
  "winnerDriverNumber": 1
}
```

**Response:**
```json
{
  "message": "Event outcome processed successfully",
  "totalBets": 10,
  "wonBets": 3,
  "lostBets": 7
}
```

## Usage Flow

1. **Create a user** with initial balance (default 1000 EUR)
2. **Query F1 sessions** to see available events and driver odds
3. **Place bets** on drivers for specific sessions
4. **Process event outcome** when race finishes to settle bets
5. **Check user balance** to see winnings

## Example Workflow

```bash
# 1. Create user
curl -X POST http://localhost:8080/person/addPerson \
  -H "Content-Type: application/json" \
  -d '{"name":"John","age":30,"balance":1000}'

# Response: {"id":"user-uuid","name":"John","age":30,"balance":1000.0}

# 2. Get F1 sessions
curl "http://localhost:8080/f1/sessions?year=2023&country=Belgium"

# 3. Place bet
curl -X POST http://localhost:8080/bets/place \
  -H "Content-Type: application/json" \
  -d '{"userId":"user-uuid","sessionKey":9158,"driverNumber":1,"amount":100}'

# 4. Process outcome (when race finishes)
curl -X POST http://localhost:8080/events/outcome \
  -H "Content-Type: application/json" \
  -d '{"sessionKey":9158,"winnerDriverNumber":1}'

# 5. Check updated balance
curl "http://localhost:8080/person/getPersonById?id=user-uuid"
```

## Betting Rules

- **Odds**: Random value between 2, 3, or 4
- **Prize Calculation**: `bet_amount × odds`
- **Bet Status**: PENDING → WON/LOST
- **Balance Update**: Automatic on event outcome processing

## Database Schema

### Tables
- **person**: Users with balance
- **bet**: User bets with status and prize
- **event_outcome**: Finished F1 events with winners

## Notes

- OpenF1 API has rate limit: 3 requests per second
- System adds 350ms delay between driver requests
- Users can bet on past F1 events
- Event outcomes can only be processed once per session
